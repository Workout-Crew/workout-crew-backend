package com.comtongsu.exercise.global.config

import com.comtongsu.exercise.global.http.OctetStreamReadMsgConverter
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(private val octetStreamReadMsgConverter: OctetStreamReadMsgConverter) :
        WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry
                .addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000",
                )
                .allowedMethods("HEAD", "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .allowedHeaders("*")
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(octetStreamReadMsgConverter)
    }
}
