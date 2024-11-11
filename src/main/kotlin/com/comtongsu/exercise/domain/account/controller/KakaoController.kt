package com.comtongsu.exercise.domain.account.controller

import com.comtongsu.exercise.domain.account.service.KakaoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/kakao")
@Tag(name = "Kakao", description = "KAKAO API")
class KakaoController(private val kakaoService: KakaoService) {

    @Operation(summary = "카카오 회원 생성")
    @PostMapping
    fun createAccount(@RequestParam("token") idToken: String): ResponseEntity<Unit> {

        kakaoService.createOrGetAccount(idToken)

        return ResponseEntity(Unit, HttpStatus.OK)
    }
}
