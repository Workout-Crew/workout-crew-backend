package com.comtongsu.exercise.domain.gathering.service

import com.comtongsu.exercise.domain.account.service.KakaoService
import com.comtongsu.exercise.domain.exerciseLog.repository.ExerciseLogRepository
import com.comtongsu.exercise.domain.gathering.dao.AccountGatheringDao
import com.comtongsu.exercise.domain.gathering.dao.GatheringDao
import com.comtongsu.exercise.domain.gathering.dto.request.GatheringRequestDto
import com.comtongsu.exercise.domain.gathering.dto.response.GatheringResponseDto
import com.comtongsu.exercise.domain.gathering.entity.AccountGathering
import com.comtongsu.exercise.domain.gathering.entity.Gathering
import com.comtongsu.exercise.domain.gathering.entity.enums.Place
import com.comtongsu.exercise.domain.gathering.exception.GatheringNotFoundException
import com.comtongsu.exercise.domain.gathering.exception.LeaderNotFoundException
import com.comtongsu.exercise.domain.gathering.repository.AccountGatheringRepository
import com.comtongsu.exercise.domain.gathering.repository.GatheringRepository
import com.comtongsu.exercise.domain.gathering.util.GatheringValidator
import com.comtongsu.exercise.global.enums.ExerciseType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GatheringService(
        private val gatheringRepository: GatheringRepository,
        private val gatheringDao: GatheringDao,
        private val accountGatheringDao: AccountGatheringDao,
        private val accountGatheringRepository: AccountGatheringRepository,
        private val kakaoService: KakaoService,
        private val exerciseLogRepository: ExerciseLogRepository,
        private val gatheringValidator: GatheringValidator,
) {
    @Transactional
    fun createGathering(token: String, request: GatheringRequestDto.GatheringRequest) {
        val account = kakaoService.getAccount(token)

        val gathering = Gathering.createGathering(request)
        gatheringRepository.save(gathering)

        accountGatheringRepository.save(
                AccountGathering.createAccountGathering(account, gathering, true))
    }

    @Transactional
    fun joinGathering(token: String, request: GatheringRequestDto.GatheringJoinRequest) {
        val account = kakaoService.getAccount(token)

        val gathering =
                gatheringRepository.findByIdOrNull(request.gatheringId)
                        ?: throw GatheringNotFoundException()

        gatheringValidator.isAlreadyJoined(gathering.accountList, account)

        accountGatheringRepository.save(
                AccountGathering.createAccountGathering(account, gathering, false))
    }

    fun getGatheringDetail(
            token: String,
            gatheringId: Long
    ): GatheringResponseDto.GatheringDetailResponse {
        val account = kakaoService.getAccount(token)

        val gathering =
                gatheringRepository.findByIdOrNull(gatheringId) ?: throw GatheringNotFoundException()
        val accountGatheringList: List<AccountGathering> =
                accountGatheringRepository.findByGathering(gathering)

        val leaderNickname = getLeaderNickname(accountGatheringList)
        val currentNumber = gathering.accountList.size
        val isLeader = gatheringValidator.isLeader(accountGatheringList, account)
        val isJoined = accountGatheringList.find { it.account == account } != null

        return gathering.toGatheringDetailResponse(leaderNickname, currentNumber, isLeader, isJoined)
    }

    fun getGatheringById(gatheringId: Long): Gathering {
        return gatheringRepository.findByIdOrNull(gatheringId) ?: throw GatheringNotFoundException()
    }

    fun getLeaderNickname(accountGatheringList: List<AccountGathering>): String {
        return accountGatheringList.find { it.isLeader == true }?.account?.nickname
                ?: throw LeaderNotFoundException()
    }

    fun getGatheringList(
            token: String,
            place: Place?,
            exerciseType: ExerciseType?
    ): GatheringResponseDto.GatheringListResponse {
        val gatheringList = gatheringDao.getGatheringListByPlaceAndExerciseType(place, exerciseType)

        return GatheringResponseDto.GatheringListResponse(
                gatheringList.map {
                    val leaderNickName =
                            it.accountList.find { it.isLeader == true }?.account?.nickname
                                    ?: throw LeaderNotFoundException()
                    val currentNumber = it.accountList.size
                    it.toGatheringContent(leaderNickName, currentNumber)
                })
    }

    fun getPossibleJoinGathering(token: String): GatheringResponseDto.GatheringListResponse {
        val account = kakaoService.getAccount(token)
        val gatheringList = gatheringDao.getPossibleJoinGathering(account)

        return GatheringResponseDto.GatheringListResponse(
                gatheringList.map {
                    val leaderNickName =
                            it.accountList.find { it.isLeader == true }?.account?.nickname
                                    ?: throw LeaderNotFoundException()
                    val currentNumber = it.accountList.size
                    it.toGatheringContent(leaderNickName, currentNumber)
                })
    }

    fun getAppliedGathering(token: String): GatheringResponseDto.GatheringListResponse {
        val account = kakaoService.getAccount(token)
        val accountGatheringList = accountGatheringDao.getMyAccountGathering(account, false)

        return GatheringResponseDto.GatheringListResponse(
                accountGatheringList.map {
                    val currentNumber = it.gathering.accountList.size
                    it.gathering.toGatheringContent(account.nickname, currentNumber)
                })
    }

    fun getMakedGathering(token: String): GatheringResponseDto.MakedGatheringListResponse {
        val account = kakaoService.getAccount(token)
        val accountGatheringList = accountGatheringDao.getMyAccountGathering(account, true)

        return GatheringResponseDto.MakedGatheringListResponse(
                accountGatheringList.map {
                    val currentNumber = it.gathering.accountList.size
                    val gatheringExerciseLog = exerciseLogRepository.existsByGathering(it.gathering)
                    it.gathering.toMyGatheringContent(account.nickname, currentNumber, gatheringExerciseLog)
                })
    }
}
