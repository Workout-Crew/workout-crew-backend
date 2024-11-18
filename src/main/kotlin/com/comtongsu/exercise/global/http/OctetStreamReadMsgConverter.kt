package com.comtongsu.exercise.global.http

import com.fasterxml.jackson.databind.ObjectMapper
import java.lang.reflect.Type
import org.springframework.http.MediaType
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter
import org.springframework.stereotype.Component

@Component
class OctetStreamReadMsgConverter(objectMapper: ObjectMapper) :
        AbstractJackson2HttpMessageConverter(objectMapper, MediaType.APPLICATION_OCTET_STREAM) {

    override fun canWrite(clazz: Class<*>, mediaType: MediaType?): Boolean {
        return false
    }

    override fun canWrite(type: Type?, clazz: Class<*>, mediaType: MediaType?): Boolean {
        return false
    }

    override fun canWrite(mediaType: MediaType?): Boolean {
        return false
    }
}
