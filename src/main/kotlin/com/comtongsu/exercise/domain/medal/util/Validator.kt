package com.comtongsu.exercise.domain.medal.util

import com.comtongsu.exercise.domain.medal.dto.response.MedalResponseDto
import com.comtongsu.exercise.domain.medal.entity.Medal
import com.comtongsu.exercise.domain.medal.entity.enums.MedalRank
import org.springframework.stereotype.Component

@Component
class Validator {
    fun setMedalCount(
            medalCountResponse: MedalResponseDto.MedalCountResponse,
            medal: Medal?
    ): MedalResponseDto.MedalCountResponse {
        if (medal == null) {
            return medalCountResponse
        }

        if (medal.medalRank == MedalRank.BRONZE) {
            medalCountResponse.bronze += 1
        }
        if (medal.medalRank == MedalRank.SILVER) {
            medalCountResponse.bronze += 1
            medalCountResponse.silver += 1
        }
        if (medal.medalRank == MedalRank.GOLD) {
            medalCountResponse.bronze += 1
            medalCountResponse.silver += 1
            medalCountResponse.gold += 1
        }
        return medalCountResponse
    }
}
