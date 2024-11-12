package com.comtongsu.exercise.domain.account.controller

import com.comtongsu.exercise.domain.account.dto.request.AccountRequestDto
import com.comtongsu.exercise.domain.account.service.AccountService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
@Tag(name = "Account", description = "계정 API")
class AccountController(private val accountService: AccountService) {

    @Operation(summary = "계정 정보 저장", description = "성별, 나이, 키, 체중을 저장합니다.")
    @PostMapping
    fun createAccountInfo(
            @RequestHeader token: String,
            @RequestBody request: AccountRequestDto.AccountRequest
    ): ResponseEntity<Unit> {
        accountService.createAccountInfo(token, request)

        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @Operation(summary = "운동 목표 저장")
    @PostMapping("/goal")
    fun createAccountGoal(
            @RequestHeader token: String,
            @RequestBody request: AccountRequestDto.AccountGoalRequest
    ): ResponseEntity<Unit> {
        accountService.createAccountGoal(token, request)

        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @Operation(summary = "닉네임 저장")
    @PostMapping("/nickname")
    fun createAccountNickname(
            @RequestHeader token: String,
            @RequestBody request: AccountRequestDto.AccountNicknameRequest
    ): ResponseEntity<Unit> {
        accountService.createAccountNickname(token, request)

        return ResponseEntity(Unit, HttpStatus.CREATED)
    }
}
