package com.comtongsu.exercise.domain.gathering.service

import com.comtongsu.exercise.domain.account.service.KakaoService
import com.comtongsu.exercise.domain.gathering.dto.request.GatheringRequestDto
import com.comtongsu.exercise.domain.gathering.entity.AccountGathering
import com.comtongsu.exercise.domain.gathering.entity.Gathering
import com.comtongsu.exercise.domain.gathering.exception.GatheringNotFoundException
import com.comtongsu.exercise.domain.gathering.repository.AccountGatheringRepository
import com.comtongsu.exercise.domain.gathering.repository.GatheringRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GatheringService(
        private val gatheringRepository: GatheringRepository,
        private val accountGatheringRepository: AccountGatheringRepository,
        private val kakaoService: KakaoService
) {
    @Transactional
    fun createGathering(token: String, request: GatheringRequestDto.GatheringRequest) {
        val account = kakaoService.getAccount(token)

        val gathering = Gathering.createGathering(request)
        gatheringRepository.save(gathering)

        accountGatheringRepository.save(
                AccountGathering.createAccountGathering(account, gathering, true))
    }

    fun getGatheringById(gatheringId: Long): Gathering {
        return gatheringRepository.findByIdOrNull(gatheringId) ?: throw GatheringNotFoundException()
    }
}
