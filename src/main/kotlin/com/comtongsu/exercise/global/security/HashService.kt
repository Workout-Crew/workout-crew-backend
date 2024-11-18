package com.comtongsu.exercise.global.security

import java.security.MessageDigest
import org.springframework.stereotype.Component

@Component
class HashService {
    fun hashId(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
