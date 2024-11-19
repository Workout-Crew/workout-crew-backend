package com.comtongsu.exercise.global.bedrock

import com.comtongsu.exercise.domain.account.dto.response.AccountResponseDto
import com.comtongsu.exercise.domain.exerciseLog.dto.response.ExerciseLogResponseDto
import com.comtongsu.exercise.global.bedrock.exception.InvokeErrorException
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.core.SdkBytes
import software.amazon.awssdk.core.exception.SdkClientException
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient
import java.util.*


@Component
class BedrockService(
    @Value("\${aws.bedrock.model.id}") private val modelId: String,
    @Value("\${aws.bedrock.system.prompt}") private val systemPrompt: String,
    private val objectMapper: ObjectMapper,
    private val bedrockClient: BedrockRuntimeClient,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(BedrockService::class.java)
    }
    fun invokeModel(accountData: AccountResponseDto.AccountForRecommendation, exerciseData: List<ExerciseLogResponseDto.ExerciseForRecommendation>): Result {
        val requestTemplate = getRequestTemplate(accountData, exerciseData)
        logger.info("Request template: $requestTemplate")

        try {
            val response = bedrockClient.invokeModel { request ->
                request
                    .body(SdkBytes.fromUtf8String(requestTemplate))
                    .modelId(modelId)
            }

            val responseBody = JSONObject(response.body().asUtf8String())
            logger.info(responseBody.toString())
            val text = JSONObject(responseBody.getJSONArray("content").getJSONObject(0).getString("text"))

            return Result(
                text.getString("type").uppercase(Locale.getDefault()),
                text.getString("description")
            )
        } catch (e: SdkClientException) {
            throw InvokeErrorException()
        }

    }

    fun getRequestTemplate(accountData: AccountResponseDto.AccountForRecommendation, exerciseData: List<ExerciseLogResponseDto.ExerciseForRecommendation>): String {
        val accountJsonData = objectMapper.writeValueAsString(accountData).escapeJson()
        val exerciseJsonData = exerciseData.joinToString(", ") {
            objectMapper.writeValueAsString(it).escapeJson()
        }

        return """
            {
                "anthropic_version": "bedrock-2023-05-31", 
                "max_tokens": 2048,
                "messages": [
                    {
                        "role": "user",
                        "content": "$systemPrompt[$accountJsonData, $exerciseJsonData]</example> based on the <example>, do answer.\""
                    }
                ]
            }
        """
    }

    fun String.escapeJson(): String = this.replace("\"", "\\\"")

    data class Result(
            val type: String,
            val description: String,
        )
}
