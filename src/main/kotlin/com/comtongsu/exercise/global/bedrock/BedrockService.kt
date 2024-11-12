package com.comtongsu.exercise.global.bedrock

import com.comtongsu.exercise.domain.account.dto.response.AccountResponseDto
import com.comtongsu.exercise.domain.exerciseLog.dto.response.ExerciseLogResponseDto
import com.comtongsu.exercise.global.bedrock.exception.InvokeErrorException
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
import org.json.JSONPointer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.core.SdkBytes
import software.amazon.awssdk.core.exception.SdkClientException
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient


@Component
class BedrockService(
    @Value("\${aws.bedrock.model.id}") private val modelId: String,
    @Value("\${aws.bedrock.system.prompt}") private val systemPrompt: String,
    private val objectMapper: ObjectMapper,
    private val bedrockClient: BedrockRuntimeClient,
) {
    fun invokeModel(accountData: AccountResponseDto.AccountForRecommendation, exerciseData: List<ExerciseLogResponseDto.ExerciseForRecommendation>): Result {
        val requestTemplate = getRequestTempate(accountData, exerciseData)

        try {
            val response = bedrockClient.invokeModel { request ->
                request
                    .body(SdkBytes.fromUtf8String(requestTemplate))
                    .modelId(modelId)
            }

            val responseBody = JSONObject(response.body().asUtf8String())
            return Result(
                responseBody.getString("type"),
                responseBody.getString("description")
            )
        } catch (e: SdkClientException) {
            throw InvokeErrorException()
        }

    }

    fun getRequestTempate(accountData: AccountResponseDto.AccountForRecommendation, exerciseData: List<ExerciseLogResponseDto.ExerciseForRecommendation>): String {
        val accountJsonData = objectMapper.writeValueAsString(accountData)
        val exerciseJsonData = exerciseData.joinToString(", ") {
            objectMapper.writeValueAsString(it)
        }

        return """
            {
                "anthropic_version": "bedrock-2023-05-31", 
                "anthropic_beta": ["computer-use-2024-10-22"] 
                "max_tokens": 2048,
                "system": $systemPrompt,
                "messages": [
                    {
                        "role": "user",
                        "content": "[$accountJsonData, $exerciseJsonData]"
                    }
                ]
            }
        """
    }

    data class Result(
            val type: String,
            val description: String,
        )
}
