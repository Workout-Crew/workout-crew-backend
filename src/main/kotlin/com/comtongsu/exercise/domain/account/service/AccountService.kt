package com.comtongsu.exercise.domain.account.service

import com.comtongsu.exercise.domain.account.dto.request.AccountRequestDto
import com.comtongsu.exercise.domain.account.entity.Account
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountService(
        private val kakaoService: KakaoService,
) {

    @Transactional
    fun createAccountInfo(accessToken: String, request: AccountRequestDto.AccountRequest) {
        val account: Account = kakaoService.getAccountFromAccessToken(accessToken)
        account.updateAccountInfo(request)
    }

    @Transactional
    fun createAccountGoal(accessToken: String, request: AccountRequestDto.AccountGoalRequest) {
        val account: Account = kakaoService.getAccountFromAccessToken(accessToken)
        account.updateAccountGoal(request)
    }
}
