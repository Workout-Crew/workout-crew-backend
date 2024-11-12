package com.comtongsu.exercise.domain.exerciseLog.dto.request

import com.comtongsu.exercise.global.enums.ExerciseType
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

class ExerciseLogRequestDto {
    data class ExerciseLogRequest(
            val title: String,
            @Schema(description = "운동 종류", example = "[\"RUNNING\", \"HEALTH\", \"SWIMMING\"]")
            val exerciseType: ExerciseType,
            @Schema(description = "운동 시작 시간", example = "2021-11-08T11:44:30.327959")
            val startTime: LocalDateTime,
            @Schema(description = "운동 종료 시간", example = "2021-11-08T11:44:30.327959")
            val endTime: LocalDateTime,
            val description: String?,
            val intensity: Int?,
            val gatheringId: Long?,
    )
}
