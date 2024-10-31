package com.comtongsu.exercise.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun api(): OpenAPI {
        val info = Info().title("CAPSTONE API").version("1.0").description("CAPSTONE API documentation")

        val accessTokenScheme =
                SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .`in`(SecurityScheme.In.HEADER)
                        .name("Authorization")

        val securityRequirement = SecurityRequirement().addList("Access Token")

        return OpenAPI()
                .info(info)
                .components(Components().addSecuritySchemes("Access Token", accessTokenScheme))
                .addSecurityItem(securityRequirement)
    }
}
