package com.comtongsu.exercise.domain.account.controller

import com.comtongsu.exercise.domain.account.dto.request.AccountRequestDto
import com.comtongsu.exercise.domain.account.service.AccountService
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
@Tag(name = "Account", description = "ACCOUNT API")
class AccountController(private val accountService: AccountService) {

    @PostMapping
    fun createAccountInfo(
            @RequestHeader accessToken: String,
            @RequestBody request: AccountRequestDto.AccountRequest
    ): ResponseEntity<Unit> {
        accountService.createAccountInfo(accessToken, request)

        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @PostMapping("/goal")
    fun createAccountGoal(
            @RequestHeader accessToken: String,
            @RequestBody request: AccountRequestDto.AccountGoalRequest
    ): ResponseEntity<Unit> {
        accountService.createAccountGoal(accessToken, request)

        return ResponseEntity(Unit, HttpStatus.CREATED)
    }
}
