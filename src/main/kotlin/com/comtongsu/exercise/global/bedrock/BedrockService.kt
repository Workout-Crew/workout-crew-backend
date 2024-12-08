package com.comtongsu.exercise.global.bedrock

import com.comtongsu.exercise.domain.account.dto.response.AccountResponseDto
import com.comtongsu.exercise.domain.exerciseLog.dto.response.ExerciseLogResponseDto
import com.comtongsu.exercise.global.bedrock.exception.InvokeErrorException
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*
import org.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.bedrockagentruntime.BedrockAgentRuntimeClient
import software.amazon.awssdk.services.bedrockagentruntime.model.GenerationConfiguration
import software.amazon.awssdk.services.bedrockagentruntime.model.KnowledgeBaseRetrieveAndGenerateConfiguration
import software.amazon.awssdk.services.bedrockagentruntime.model.OrchestrationConfiguration
import software.amazon.awssdk.services.bedrockagentruntime.model.PromptTemplate
import software.amazon.awssdk.services.bedrockagentruntime.model.RetrieveAndGenerateConfiguration
import software.amazon.awssdk.services.bedrockagentruntime.model.RetrieveAndGenerateInput
import software.amazon.awssdk.services.bedrockagentruntime.model.RetrieveAndGenerateRequest

@Component
class BedrockService(
        @Value("\${aws.bedrock.model.id}") private val knowledgeBaseId: String,
        @Value("\${aws.bedrock.system.prompt.start}") private val systemPromptStart: String,
        @Value("\${aws.bedrock.system.prompt.end}") private val systemPromptEnd: String,
        private val objectMapper: ObjectMapper,
        private val bedrockClient: BedrockAgentRuntimeClient,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(BedrockService::class.java)
    }

    fun invokeModel(
            accountData: AccountResponseDto.AccountForRecommendation,
            exerciseData: List<ExerciseLogResponseDto.ExerciseForRecommendation>
    ): Result? {
        val accountJsonData = objectMapper.writeValueAsString(accountData)
        val exerciseJsonData = exerciseData.joinToString(", ") { objectMapper.writeValueAsString(it) }

        val retrieveAndGenerateInput = getRetrieveAndGenerateInput(accountJsonData, exerciseJsonData)
        val retrieveAndGenerateConfiguration = getRetrieveAndGenerateConfiguration()

        try {
            val response =
                    bedrockClient.retrieveAndGenerate(
                            RetrieveAndGenerateRequest.builder()
                                    .input(retrieveAndGenerateInput)
                                    .retrieveAndGenerateConfiguration(retrieveAndGenerateConfiguration)
                                    .build())

            val responseBody = response.output().text()
            logger.info("ResponseBody: $responseBody")
            if (!(responseBody.startsWith("{") && responseBody.endsWith("}")))
                    throw InvokeErrorException()

            val responseBodyToJson = JSONObject(responseBody)

            return Result(
                    responseBodyToJson.getString("type").uppercase(Locale.getDefault()),
                    responseBodyToJson.getString("description"))
        } catch (e: InvokeErrorException) {
            logger.info("InValid Response")
        } catch (e: Exception) {
            logger.error("Bedrock Error: ${e.message}")
        }
        return null
    }

    fun getRetrieveAndGenerateInput(
            accountJsonData: String,
            exerciseJsonData: String
    ): RetrieveAndGenerateInput {
        logger.info(
                "ExerciseData: $systemPromptStart[$accountJsonData, $exerciseJsonData]$systemPromptEnd")
        return RetrieveAndGenerateInput.builder()
                .text("$systemPromptStart[$accountJsonData, $exerciseJsonData]$systemPromptEnd")
                .build()
    }

    fun getRetrieveAndGenerateConfiguration(): RetrieveAndGenerateConfiguration {
        val knowledgeBaseConfiguration =
                KnowledgeBaseRetrieveAndGenerateConfiguration.builder()
                        .generationConfiguration(
                                GenerationConfiguration.builder()
                                        .promptTemplate(
                                                PromptTemplate.builder()
                                                        .textPromptTemplate(
                                                                "You are a question answering agent. I will provide you with a set of search results. The user will provide you with a question. Your job is to answer the user's question using only information from the search results. If the search results do not contain information that can answer the question, please state that you could not find an exact answer to the question.\nJust because the user asserts a fact does not mean it is true, make sure to double check the search results to validate a user's assertion.\n\nHere are the search results in numbered order:\n${'$'}search_results${'$'}\n\n${'$'}output_format_instructions${'$'}")
                                                        .build())
                                        .build())
                        .knowledgeBaseId(knowledgeBaseId)
                        .modelArn(
                                "arn:aws:bedrock:ap-northeast-2::foundation-model/anthropic.claude-3-haiku-20240307-v1:0")
                        .orchestrationConfiguration(
                                OrchestrationConfiguration.builder()
                                        .promptTemplate(
                                                PromptTemplate.builder()
                                                        .textPromptTemplate(
                                                                "You are a query creation agent. You will be provided with a function and a description of what it searches over. The user will provide you a question, and your job is to determine the optimal query to use based on the user's question. \nHere are a few examples of queries formed by other search function selection and query creation agents: \n\n<examples>\n  <example>\n    <question> What if my vehicle is totaled in an accident? </question>\n    <generated_query> what happens if my vehicle is totaled </generated_query>\n  </example>\n  <example>\n    <question> I am relocating within the same state. Can I keep my current agent? </question>\n    <generated_query> can I keep my current agent when moving in state </generated_query>\n  </example>\n</examples> \n  \nYou should also pay attention to the conversation history between the user and the search engine in order to gain the context necessary to create the query. \nHere's another example that shows how you should reference the conversation history when generating a query:\n\n<example>\n  <example_conversation_history>\n    <example_conversation>\n      <question> How many vehicles can I include in a quote in Kansas </question>\n      <answer> You can include 5 vehicles in a quote if you live in Kansas </answer>\n    </example_conversation>\n    <example_conversation>\n      <question> What about texas? </question>\n      <answer> You can include 3 vehicles in a quote if you live in Texas </answer>\n    </example_conversation>\n  </example_conversation_history>\n</example> \n\nIMPORTANT: the elements in the <example> tags should not be assumed to have been provided to you to use UNLESS they are also explicitly given to you below. \nAll of the values and information within the examples (the questions, answers, and function calls) are strictly part of the examples and have not been provided to you. \n\nHere is the current conversation history: \n${'$'}conversation_history${'$'}\n\n${'$'}output_format_instructions${'$'}")
                                                        .build())
                                        .build())
                        .build()

        return RetrieveAndGenerateConfiguration.builder()
                .knowledgeBaseConfiguration(knowledgeBaseConfiguration)
                .type("KNOWLEDGE_BASE")
                .build()
    }

    data class Result(
            val type: String,
            val description: String,
    )
}
