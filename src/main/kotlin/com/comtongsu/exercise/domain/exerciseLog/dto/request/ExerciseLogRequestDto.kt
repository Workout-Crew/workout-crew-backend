package com.comtongsu.exercise.domain.exerciseLog.dto.request

import com.comtongsu.exercise.global.enums.ExerciseType
import java.time.LocalDateTime

class ExerciseLogRequestDto {
    data class ExerciseLogRequest(
            val title: String,
            val exerciseType: ExerciseType,
            val startTime: LocalDateTime,
            val endTime: LocalDateTime,
            val description: String?,
            val intensity: Int?,
            val gatheringId: Long?,
    )
}
