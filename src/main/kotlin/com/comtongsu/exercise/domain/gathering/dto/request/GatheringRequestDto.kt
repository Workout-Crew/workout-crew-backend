package com.comtongsu.exercise.domain.gathering.dto.request

import com.comtongsu.exercise.domain.gathering.entity.enums.Place
import com.comtongsu.exercise.global.enums.ExerciseType
import java.time.LocalDateTime

class GatheringRequestDto {
    data class GatheringRequest(
            val title: String,
            val place: Place,
            val exerciseType: ExerciseType,
            val startDate: LocalDateTime,
            val content: String,
    )
}
