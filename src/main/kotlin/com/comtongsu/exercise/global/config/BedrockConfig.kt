package com.comtongsu.exercise.global.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.bedrockagentruntime.BedrockAgentRuntimeClient

@Configuration
class BedrockConfig(
        @Value("\${cloud.aws.credentials.access.key}") private val accessKey: String,
        @Value("\${cloud.aws.credentials.secret.key}") private val secretKey: String,
) {
    @Bean
    fun bedrockClient(): BedrockAgentRuntimeClient {
        return BedrockAgentRuntimeClient.builder()
                .credentialsProvider(
                        StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.AP_NORTHEAST_2)
                .build()
    }
}
