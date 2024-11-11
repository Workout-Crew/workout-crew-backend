package com.comtongsu.exercise.domain.medal.service

import com.comtongsu.exercise.domain.account.entity.Account
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
        private val medalTypeList: List<MedalType> = MedalType.values().toList()
) {

    fun getMedalMission(token: String, medalType: MedalType): MedalResponseDto.MedalMissionResponse {
        val account = kakaoService.getAccount(token)

        val currentValue =
                when (medalType) {
                    MedalType.WRITE_EXERCISE_LOG -> medalDao.getExerciseLogCount(account)
                    MedalType.WRITE_GATHERING_EXERCISE_LOG -> medalDao.getGatheringExerciseLogCount(account)
                    MedalType.MAKE_GATHERING -> medalDao.getGatheringCount(account)
                    MedalType.WRITE_VARIOUS_EXERCISE_TYPE -> medalDao.getExerciseLogTypeCount(account)
                }

        return MedalResponseDto.MedalMissionResponse(
                medalType, currentValue, medalDao.getNextMedal(currentValue, medalType)?.value)
    }

    fun getMedalCount(token: String): MedalResponseDto.MedalCountResponse {
        val account = kakaoService.getAccount(token)
        val medalCountResponse = MedalResponseDto.MedalCountResponse(0, 0, 0)

        medalTypeList.forEach { medalType ->
            val currentMedal = getCurrentMedalByMedalType(account, medalType)
            val medalValue = validator.setMedalCount(currentMedal)

            medalCountResponse.apply {
                bronze += medalValue.bronze
                silver += medalValue.silver
                gold += medalValue.gold
            }
        }

        return medalCountResponse
    }

    fun getMyMedal(token: String): MedalResponseDto.MyMedalResponse {
        val account = kakaoService.getAccount(token)

        return MedalResponseDto.MyMedalResponse(
                medalTypeList.map { medalType ->
                    val currentMedal = getCurrentMedalByMedalType(account, medalType)
                    MedalResponseDto.MyMedal(medalType, currentMedal?.medalRank)
                })
    }

    fun getTotalMedal(): MedalResponseDto.TotalMedalResponse {
        val medalList = medalRepository.findAll()

        return MedalResponseDto.TotalMedalResponse(
                medalList.map { medal ->
                    MedalResponseDto.MedalInfo(medal.medalType, medal.image, medal.value, medal.medalRank)
                })
    }

    fun getCurrentMedalByMedalType(account: Account, medalType: MedalType): Medal? {
        val currentValue =
                when (medalType) {
                    MedalType.WRITE_EXERCISE_LOG -> medalDao.getExerciseLogCount(account)
                    MedalType.WRITE_GATHERING_EXERCISE_LOG -> medalDao.getGatheringExerciseLogCount(account)
                    MedalType.MAKE_GATHERING -> medalDao.getGatheringCount(account)
                    MedalType.WRITE_VARIOUS_EXERCISE_TYPE -> medalDao.getExerciseLogTypeCount(account)
                }
        return medalDao.getCurrentMedal(currentValue, medalType)
    }
}
