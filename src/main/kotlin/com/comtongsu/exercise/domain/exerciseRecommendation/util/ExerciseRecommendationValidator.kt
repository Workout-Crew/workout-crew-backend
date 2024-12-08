package com.comtongsu.exercise.domain.exerciseRecommendation.util

import com.comtongsu.exercise.global.enums.ExerciseType
import org.springframework.stereotype.Component

@Component
class ExerciseRecommendationValidator {
    fun isValidExerciseType(value: String): Boolean {
        return try {
            ExerciseType.valueOf(value)
            true
        } catch (e: Exception) {
            false
        }
    }
}
