package com.comtongsu.exercise.domain.gathering.dto.response

import com.comtongsu.exercise.domain.gathering.entity.enums.Place
import com.comtongsu.exercise.global.enums.ExerciseType
import java.time.LocalDateTime

class GatheringResponseDto {
    data class GatheringDetailResponse(
            val title: String?,
            val content: String?,
            val leaderNickname: String?,
            val place: Place?,
            val exerciseType: ExerciseType?,
            val startDate: LocalDateTime?,
            val maximumNumber: Int?,
            val isLeader: Boolean,
    )

    data class GatheringListResponse(val gatheringList: List<GatheringContent>)

    data class GatheringContent(
            val title: String?,
            val description: String?,
            val leaderNickname: String?,
            val place: Place?,
            val maximumNumber: Int?,
            val startDate: LocalDateTime?
    )
}
