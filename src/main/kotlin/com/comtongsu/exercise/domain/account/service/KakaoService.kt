package com.comtongsu.exercise.domain.account.service

import com.comtongsu.exercise.domain.account.dto.response.KakaoResponseDto
import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.account.exception.AccountNotFoundException
import com.comtongsu.exercise.domain.account.repository.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class KakaoService(private val accountRepository: AccountRepository) {
    @Transactional
    fun createOrGetAccount(id: String): KakaoResponseDto.KakaoResponse {
        val account =
                accountRepository.findByIdOrNull(id) ?: accountRepository.save(Account.createAccount(id))

        return account.toKakaoResponse()
    }

    fun getAccount(id: String): Account {
        return accountRepository.findByIdOrNull(id) ?: throw AccountNotFoundException()
    }
}
