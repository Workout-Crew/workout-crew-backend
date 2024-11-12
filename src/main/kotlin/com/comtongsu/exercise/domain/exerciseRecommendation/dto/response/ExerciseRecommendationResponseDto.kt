package com.comtongsu.exercise.domain.exerciseRecommendation.dto.response

import com.comtongsu.exercise.global.enums.ExerciseType

class ExerciseRecommendationResponseDto {
    data class ExerciseRecommendationResponse(
            val exerciseType: ExerciseType?,
            val intensity: Int?,
            val description: String?,
    )
}
