package com.comtongsu.exercise.domain.account.service

import com.comtongsu.exercise.domain.account.dto.response.KakaoResponseDto
import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.account.exception.KakaoAccountNotFoundException
import com.comtongsu.exercise.domain.account.repository.AccountRepository
import io.netty.handler.codec.http.HttpHeaderValues
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
@Transactional(readOnly = true)
class KakaoService(
        @Value("\${kauth.rest.api.key}") val KAUTH_REST_API_KEY: String,
        @Value("\${kauth.rest.api.redirect.uri}") val KAUTH_REST_API_REDIRECT_URI: String,
        @Value("\${kauth.token.url.host}") val KAUTH_TOKEN_URL_HOST: String,
        @Value("\${kauth.user.url.host}") val KAUTH_USER_URL_HOST: String,
        private val accountRepository: AccountRepository
) {

    fun getAccessTokenFromKakao(code: String): String {
        val kakaoTokenResponse: KakaoResponseDto.KakaoTokenResponse? =
                WebClient.create(KAUTH_TOKEN_URL_HOST)
                        .post()
                        .uri { uriBuilder ->
                            uriBuilder
                                    .scheme("https")
                                    .path("/oauth/token")
                                    .queryParam("grant_type", "authorization_code")
                                    .queryParam("client_id", KAUTH_REST_API_KEY)
                                    .queryParam("redirect_uri", KAUTH_REST_API_REDIRECT_URI)
                                    .queryParam("code", code)
                                    .build(true)
                        }
                        .header(
                                HttpHeaders.CONTENT_TYPE,
                                HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                        .retrieve()
                        .bodyToMono<KakaoResponseDto.KakaoTokenResponse>()
                        .block()

        return kakaoTokenResponse?.accessToken ?: throw KakaoAccountNotFoundException()
    }

    fun getUserInfoFromKakao(accessToken: String): Long {
        val kakaoUserInfoResponse: KakaoResponseDto.KakaoUserInfoResponse? =
                WebClient.create(KAUTH_USER_URL_HOST)
                        .get()
                        .uri { uriBuilder -> uriBuilder.scheme("https").path("/v2/user/me").build(true) }
                        .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
                        .header(
                                HttpHeaders.CONTENT_TYPE,
                                HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                        .retrieve()
                        .bodyToMono<KakaoResponseDto.KakaoUserInfoResponse>()
                        .block()

        return kakaoUserInfoResponse?.id ?: throw KakaoAccountNotFoundException()
    }

    @Transactional
    fun createOrGetAccount(id: Long) {
        if (accountRepository.existsById(id)) return

        accountRepository.save(Account.createAccount(id))
    }
}
