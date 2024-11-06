package com.comtongsu.exercise.domain.medal.util

import com.comtongsu.exercise.domain.medal.dto.response.MedalResponseDto
import com.comtongsu.exercise.domain.medal.entity.Medal
import org.springframework.stereotype.Component

@Component
class Validator {
    fun setMedalCount(medal: Medal?): MedalResponseDto.MedalCountResponse {
        val medalCountResponse = MedalResponseDto.MedalCountResponse(0, 0, 0)
        medal?.let {
            val rank = it.medalRank.rank
            if (rank >= 1) medalCountResponse.bronze += 1
            if (rank >= 2) medalCountResponse.silver += 1
            if (rank >= 3) medalCountResponse.gold += 1
        }
        return medalCountResponse
    }
}
