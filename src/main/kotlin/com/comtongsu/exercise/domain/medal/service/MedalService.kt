package com.comtongsu.exercise.domain.medal.service

import com.comtongsu.exercise.domain.account.service.KakaoService
import com.comtongsu.exercise.domain.medal.dao.MedalDao
import com.comtongsu.exercise.domain.medal.dto.response.MedalResponseDto
import com.comtongsu.exercise.domain.medal.entity.Medal
import com.comtongsu.exercise.domain.medal.entity.enums.MedalType
import com.comtongsu.exercise.domain.medal.repository.MedalRepository
import com.comtongsu.exercise.domain.medal.util.Validator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MedalService(
        private val kakaoService: KakaoService,
        private val medalDao: MedalDao,
        private val validator: Validator,
        private val medalRepository: MedalRepository,
) {
    fun getExerciseLogCount(accessToken: String): MedalResponseDto.MedalMissionResponse {
        val account = kakaoService.getAccountFromAccessToken(accessToken)
        val currentValue = medalDao.getExerciseLogCount(account)

        return MedalResponseDto.MedalMissionResponse(
                medalType = MedalType.WRITE_EXERCISE_LOG,
                currentValue = currentValue,
                nextValue = medalDao.getNextMedal(currentValue, MedalType.WRITE_EXERCISE_LOG)?.value)
    }

    fun getGatheringExerciseLogCount(accessToken: String): MedalResponseDto.MedalMissionResponse {
        val account = kakaoService.getAccountFromAccessToken(accessToken)
        val currentValue = medalDao.getGatheringExerciseLogCount(account)

        return MedalResponseDto.MedalMissionResponse(
                medalType = MedalType.WRITE_GATHERING_EXERCISE_LOG,
                currentValue = currentValue,
                nextValue =
                        medalDao.getNextMedal(currentValue, MedalType.WRITE_GATHERING_EXERCISE_LOG)?.value)
    }

    fun getGatheringCount(accessToken: String): MedalResponseDto.MedalMissionResponse {
        val account = kakaoService.getAccountFromAccessToken(accessToken)
        val currentValue = medalDao.getGatheringCount(account)

        return MedalResponseDto.MedalMissionResponse(
                medalType = MedalType.MAKE_GATHERING,
                currentValue = currentValue,
                nextValue = medalDao.getNextMedal(currentValue, MedalType.MAKE_GATHERING)?.value)
    }

    fun getExerciseLogTypeCount(accessToken: String): MedalResponseDto.MedalMissionResponse {
        val account = kakaoService.getAccountFromAccessToken(accessToken)
        val currentValue = medalDao.getExerciseLogTypeCount(account)

        return MedalResponseDto.MedalMissionResponse(
                medalType = MedalType.WRITE_VARIOUS_EXERCISE_TYPE,
                currentValue = currentValue,
                nextValue =
                        medalDao.getNextMedal(currentValue, MedalType.WRITE_VARIOUS_EXERCISE_TYPE)?.value)
    }

    fun getMedalCount(accessToken: String): MedalResponseDto.MedalCountResponse {
        val account = kakaoService.getAccountFromAccessToken(accessToken)
        var medalCountResponse = MedalResponseDto.MedalCountResponse(0, 0, 0)

        var currentValue = medalDao.getExerciseLogCount(account)
        var currentMedal = medalDao.getCurrentMedal(currentValue, MedalType.WRITE_EXERCISE_LOG)
        medalCountResponse = validator.setMedalCount(medalCountResponse, currentMedal)

        currentValue = medalDao.getGatheringExerciseLogCount(account)
        currentMedal = medalDao.getCurrentMedal(currentValue, MedalType.WRITE_GATHERING_EXERCISE_LOG)
        medalCountResponse = validator.setMedalCount(medalCountResponse, currentMedal)

        currentValue = medalDao.getGatheringCount(account)
        currentMedal = medalDao.getCurrentMedal(currentValue, MedalType.MAKE_GATHERING)
        medalCountResponse = validator.setMedalCount(medalCountResponse, currentMedal)

        currentValue = medalDao.getExerciseLogTypeCount(account)
        currentMedal = medalDao.getCurrentMedal(currentValue, MedalType.WRITE_VARIOUS_EXERCISE_TYPE)
        medalCountResponse = validator.setMedalCount(medalCountResponse, currentMedal)

        return medalCountResponse
    }

    fun getTotalMedal(accessToken: String): MedalResponseDto.TotalMedalResponse {
        val account = kakaoService.getAccountFromAccessToken(accessToken)
        val totalMedalResponse: MutableList<MedalResponseDto.TotalMedal> = mutableListOf()

        var medalList: List<Medal> = medalRepository.findByMedalType(MedalType.WRITE_EXERCISE_LOG)
        var medalInfo =
                medalList.map { MedalResponseDto.MedalInfo(it.medalType, it.image, it.value, it.medalRank) }
        var currentValue = medalDao.getExerciseLogCount(account)
        var currentMedal = medalDao.getCurrentMedal(currentValue, MedalType.WRITE_EXERCISE_LOG)
        totalMedalResponse.add(MedalResponseDto.TotalMedal(medalInfo, currentMedal?.medalRank))

        medalList = medalRepository.findByMedalType(MedalType.WRITE_GATHERING_EXERCISE_LOG)
        medalInfo =
                medalList.map { MedalResponseDto.MedalInfo(it.medalType, it.image, it.value, it.medalRank) }
        currentValue = medalDao.getGatheringExerciseLogCount(account)
        currentMedal = medalDao.getCurrentMedal(currentValue, MedalType.WRITE_GATHERING_EXERCISE_LOG)
        totalMedalResponse.add(MedalResponseDto.TotalMedal(medalInfo, currentMedal?.medalRank))

        medalList = medalRepository.findByMedalType(MedalType.MAKE_GATHERING)
        medalInfo =
                medalList.map { MedalResponseDto.MedalInfo(it.medalType, it.image, it.value, it.medalRank) }
        currentValue = medalDao.getGatheringCount(account)
        currentMedal = medalDao.getCurrentMedal(currentValue, MedalType.MAKE_GATHERING)
        totalMedalResponse.add(MedalResponseDto.TotalMedal(medalInfo, currentMedal?.medalRank))

        medalList = medalRepository.findByMedalType(MedalType.WRITE_VARIOUS_EXERCISE_TYPE)
        medalInfo =
                medalList.map { MedalResponseDto.MedalInfo(it.medalType, it.image, it.value, it.medalRank) }
        currentValue = medalDao.getExerciseLogTypeCount(account)
        currentMedal = medalDao.getCurrentMedal(currentValue, MedalType.WRITE_VARIOUS_EXERCISE_TYPE)
        totalMedalResponse.add(MedalResponseDto.TotalMedal(medalInfo, currentMedal?.medalRank))

        return MedalResponseDto.TotalMedalResponse(totalMedalResponse)
    }
}
