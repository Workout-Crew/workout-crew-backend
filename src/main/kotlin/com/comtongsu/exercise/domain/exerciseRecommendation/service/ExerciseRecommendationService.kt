package com.comtongsu.exercise.domain.exerciseRecommendation.service

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.account.service.KakaoService
import com.comtongsu.exercise.domain.exerciseRecommendation.dto.ExerciseRecommendationResponseDto
import com.comtongsu.exercise.domain.exerciseRecommendation.exception.ExerciseRecommendationNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ExerciseRecommendationService(private val kakaoService: KakaoService) {
    fun getExerciseRecommendation(
            accessToken: String
    ): ExerciseRecommendationResponseDto.ExerciseRecommendationResponse {
        val account: Account = kakaoService.getAccountFromAccessToken(accessToken)

        val exerciseRecommendation =
                account.exerciseRecommendationList.maxByOrNull { it.createdDate }
                        ?: throw ExerciseRecommendationNotFoundException()

        return ExerciseRecommendationResponseDto.ExerciseRecommendationResponse(
                exerciseRecommendation.exerciseType,
                exerciseRecommendation.intensity,
                exerciseRecommendation.description)
    }
}
