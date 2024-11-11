package com.comtongsu.exercise.domain.account.service

import com.comtongsu.exercise.domain.account.dto.request.AccountRequestDto
import com.comtongsu.exercise.domain.account.dto.response.AccountResponseDto
import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.account.repository.AccountRepository
import com.comtongsu.exercise.global.enums.RecommendationGoal
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountService(
        private val kakaoService: KakaoService,
        private val accountRepository: AccountRepository
) {

    @Transactional
    fun createAccountInfo(token: String, request: AccountRequestDto.AccountRequest) {
        val account: Account = kakaoService.getAccount(token)
        account.updateAccountInfo(request)
    }

    @Transactional
    fun createAccountGoal(token: String, request: AccountRequestDto.AccountGoalRequest) {
        val account: Account = kakaoService.getAccount(token)
        account.updateAccountGoal(request)
    }

    fun getAccountForRecommendation(account: Account): AccountResponseDto.AccountForRecommendation {
        return AccountResponseDto.AccountForRecommendation(
                account.height, account.weight, RecommendationGoal.OVERLOAD.text)
    }

    fun findAllAccount(): List<Account> {
        return accountRepository.findAll()
    }
}
