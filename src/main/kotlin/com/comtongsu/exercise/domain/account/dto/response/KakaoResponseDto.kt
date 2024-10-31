package com.comtongsu.exercise.domain.account.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class KakaoResponseDto {
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class KakaoTokenResponse(
            @JsonProperty("token_type") val tokenType: String,
            @JsonProperty("access_token") val accessToken: String,
            @JsonProperty("id_token") val idToken: String?,
            @JsonProperty("expires_in") val expiresIn: Int,
            @JsonProperty("refresh_token") val refreshToken: String,
            @JsonProperty("refresh_token_expires_in") val refreshTokenExpiresIn: Int,
            @JsonProperty("scope") val scope: String?,
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class KakaoUserInfoResponse(
            @JsonProperty("id") val id: Long,
    )
}
