package com.comtongsu.exercise.domain.medal.dto.response

import com.comtongsu.exercise.domain.medal.entity.enums.MedalRank
import com.comtongsu.exercise.domain.medal.entity.enums.MedalType

class MedalResponseDto {
    data class MedalMissionResponse(
            val medalType: MedalType,
            val currentValue: Int,
            val nextValue: Int?,
    )

    data class MedalCountResponse(
            var gold: Int,
            var silver: Int,
            var bronze: Int,
    )

    data class MyMedalResponse(
            var totalMedal: List<MyMedal>,
    )

    data class MyMedal(
            val medalType: MedalType,
            val userRank: MedalRank?,
    )

    data class TotalMedalResponse(
            val medalInfo: List<MedalInfo>,
    )

    data class MedalInfo(
            val medalType: MedalType?,
            val image: String?,
            val value: Int?,
            val medalRank: MedalRank,
    )
}
