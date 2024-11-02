package com.comtongsu.exercise.domain.exerciseRecommendation.exception

import com.comtongsu.exercise.global.error.ErrorCode
import com.comtongsu.exercise.global.error.exception.EntityNotFoundException

class ExerciseRecommendationNotFoundException: EntityNotFoundException(ErrorCode.EXERCISE_RECOMMENDATION_NOT_FOUND) {
}