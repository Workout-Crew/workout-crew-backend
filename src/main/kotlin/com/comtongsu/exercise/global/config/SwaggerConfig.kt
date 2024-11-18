package com.comtongsu.exercise.global.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun api(): OpenAPI {
        val info = Info().title("CAPSTONE API").version("1.0").description("CAPSTONE API documentation")

        val local = Server().url("http://localhost:8080")
        val prod = Server().url("https://uoscs-capstone.click")

        return OpenAPI().info(info).servers(listOf(local, prod))
    }
}
