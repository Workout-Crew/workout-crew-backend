package com.comtongsu.exercise.domain.medal.dto.response

import com.comtongsu.exercise.domain.medal.entity.enums.MedalRank
import com.comtongsu.exercise.domain.medal.entity.enums.MedalType

class MedalResponseDto {
    data class MedalMissionResponse(
            val medalType: MedalType?,
            val currentValue: Int,
            val nextValue: Int?,
    )

    data class MedalCountResponse(
            var gold: Int,
            var silver: Int,
            var bronze: Int,
    )

    data class TotalMedalResponse(
            var totalMedal: MutableList<TotalMedal>,
    )

    data class TotalMedal(
            val medalList: List<MedalInfo>,
            val userRank: MedalRank?,
    )

    data class MedalInfo(
            val medalType: MedalType?,
            val image: String?,
            val value: Int?,
            val medalRank: MedalRank?,
    )
}
