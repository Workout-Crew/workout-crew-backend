package com.comtongsu.exercise.domain.account.controller

import com.comtongsu.exercise.domain.account.dto.response.AccountResponseDto
import com.comtongsu.exercise.domain.account.service.KakaoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kakao")
@Tag(name = "Kakao", description = "KAKAO API")
class KakaoController(private val kakaoService: KakaoService) {

    @Operation(
            summary = "카카오 로그인",
            description =
                    "\"https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=\${REST_API_KEY}&redirect_uri=\${REDIRECT_URI}\"로 접속하여 로그인을 진행합니다.")
    @GetMapping("/callback")
    fun getAccessTokenFromKakao(
            @RequestParam("code") code: String
    ): ResponseEntity<AccountResponseDto.AccessTokenResponse> {
        val accessToken = kakaoService.getAccessTokenFromKakao(code)
        val id = kakaoService.getUserInfoFromKakao(accessToken)

        kakaoService.createOrGetAccount(id)

        return ResponseEntity(
                AccountResponseDto.AccessTokenResponse(accessToken), org.springframework.http.HttpStatus.OK)
    }
}
