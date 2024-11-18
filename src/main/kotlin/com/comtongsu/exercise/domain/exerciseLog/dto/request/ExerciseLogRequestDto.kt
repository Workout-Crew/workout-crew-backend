package com.comtongsu.exercise.domain.exerciseLog.dto.request

import com.comtongsu.exercise.global.enums.ExerciseType
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

class ExerciseLogRequestDto {
    data class ExerciseLogRequest
    @JsonCreator
    constructor(
            @JsonProperty("title") val title: String,
            @Schema(description = "운동 종류", example = "RUNNING")
            @JsonProperty("exerciseType")
            val exerciseType: ExerciseType,
            @Schema(description = "운동 시작 시간", example = "yyyy-MM-dd'T'HH:mm:ss.SSS")
            @JsonProperty("startTime")
            val startTime: LocalDateTime?,
            @Schema(description = "운동 종료 시간", example = "yyyy-MM-dd'T'HH:mm:ss.SSS")
            @JsonProperty("endTime")
            val endTime: LocalDateTime?,
            @JsonProperty("description") val description: String?,
            @JsonProperty("intensity") val intensity: Int?,
            @JsonProperty("gatheringId") val gatheringId: Long?,
    )
}
