package com.comtongsu.exercise.domain.medal.service

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.account.service.KakaoService
import com.comtongsu.exercise.domain.medal.dao.MedalDao
import com.comtongsu.exercise.domain.medal.dto.response.MedalResponseDto
import com.comtongsu.exercise.domain.medal.entity.Medal
import com.comtongsu.exercise.domain.medal.entity.enums.MedalRank
import com.comtongsu.exercise.domain.medal.entity.enums.MedalType
import com.comtongsu.exercise.domain.medal.repository.MedalRepository
import com.comtongsu.exercise.domain.medal.util.MedalValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MedalService(
        private val kakaoService: KakaoService,
        private val medalDao: MedalDao,
        private val medalValidator: MedalValidator,
        private val medalRepository: MedalRepository,
        private val medalTypeList: List<MedalType> = MedalType.entries
) {

    fun getMedalMission(token: String): MedalResponseDto.MedalMissionListResponse {
        val account = kakaoService.getAccount(token)

        val currentValueList =
                mutableListOf(
                        medalDao.getExerciseLogCount(account),
                        medalDao.getGatheringExerciseLogCount(account),
                        medalDao.getGatheringCount(account),
                        medalDao.getExerciseLogTypeCount(account))

        return MedalResponseDto.MedalMissionListResponse(
                currentValueList
                        .mapIndexed { idx, currentValue ->
                            val nextMedal = medalDao.getNextMedal(currentValue, medalTypeList[idx])

                            MedalResponseDto.MedalMission(
                                    medalTypeList[idx], nextMedal?.medalRank, currentValue, nextMedal?.value)
                        }
                        .filter { mission -> mission.medalRank != MedalRank.GOLD })
    }

    fun getMedalCount(token: String): MedalResponseDto.MedalCountResponse {
        val account = kakaoService.getAccount(token)
        val medalCountResponse = MedalResponseDto.MedalCountResponse(0, 0, 0)

        medalTypeList.forEach { medalType ->
            val currentMedal = getCurrentMedalByMedalType(account, medalType)
            val medalValue = medalValidator.setMedalCount(currentMedal)

            medalCountResponse.apply {
                bronze += medalValue.bronze
                silver += medalValue.silver
                gold += medalValue.gold
            }
        }

        return medalCountResponse
    }

    fun getTotalMedal(token: String): MedalResponseDto.TotalMedalResponse {
        val account = kakaoService.getAccount(token)

        val medalList = medalRepository.findAll()

        return MedalResponseDto.TotalMedalResponse(
                medalList.map { medal ->
                    val currentMedal = getCurrentMedalByMedalType(account, medal.medalType!!)
                    var alreadyGet = false
                    if (medal.medalRank.rank <= (currentMedal?.medalRank?.rank ?: 0)) alreadyGet = true

                    MedalResponseDto.MedalInfo(
                            medal.medalType, medal.image, medal.value, medal.medalRank, alreadyGet)
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
