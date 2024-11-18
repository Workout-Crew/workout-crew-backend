package com.comtongsu.exercise.domain.gathering.dto.request

import com.comtongsu.exercise.domain.gathering.entity.enums.Place
import com.comtongsu.exercise.global.enums.ExerciseType
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

class GatheringRequestDto {
    data class GatheringRequest
    @JsonCreator
    constructor(
            @JsonProperty("title") val title: String,
            @JsonProperty("place") val place: Place,
            @JsonProperty("exerciseType") val exerciseType: ExerciseType,
            @JsonProperty("startDate") val startDate: LocalDateTime,
            @JsonProperty("content") val content: String,
            @JsonProperty("maximumNumber") val maximumNumber: Int,
    )

    data class GatheringJoinRequest
    @JsonCreator
    constructor(@JsonProperty("gatheringId") val gatheringId: Long)
}
