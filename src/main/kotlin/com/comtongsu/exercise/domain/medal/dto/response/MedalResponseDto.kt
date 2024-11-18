package com.comtongsu.exercise.domain.medal.dto.response

import com.comtongsu.exercise.domain.medal.entity.enums.MedalRank
import com.comtongsu.exercise.domain.medal.entity.enums.MedalType

class MedalResponseDto {
    data class MedalMissionListResponse(
            val medalMissionList: List<MedalMission>,
    )

    data class MedalMission(
            val medalType: MedalType,
            val medalRank: MedalRank?,
            val currentValue: Int,
            val nextValue: Int?,
    )

    data class MedalCountResponse(
            var gold: Int,
            var silver: Int,
            var bronze: Int,
    )

    data class TotalMedalResponse(
            val medalInfo: List<MedalInfo>,
    )

    data class MedalInfo(
            val medalType: MedalType?,
            val image: String?,
            val value: Int?,
            val medalRank: MedalRank,
            val alreadyGet: Boolean,
    )
}
