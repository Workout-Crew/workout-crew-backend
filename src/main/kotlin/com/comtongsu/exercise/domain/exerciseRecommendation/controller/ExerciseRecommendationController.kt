package com.comtongsu.exercise.domain.exerciseRecommendation.controller

import com.comtongsu.exercise.domain.exerciseRecommendation.dto.ExerciseRecommendationResponseDto
import com.comtongsu.exercise.domain.exerciseRecommendation.service.ExerciseRecommendationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/exerciserecommendation")
@Tag(name = "Exercise Recommendation", description = "운동 추천 API")
class ExerciseRecommendationController(
        private val exerciseRecommendationService: ExerciseRecommendationService
) {

    @Operation(summary = "추천 운동 조회", description = "추천된 운동을 조회합니다. 주기적으로 데이터를 통헤 추천 운동을 저장합니다.")
    @GetMapping
    fun getExerciseRecommendation(
            @RequestHeader accessToken: String
    ): ResponseEntity<ExerciseRecommendationResponseDto.ExerciseRecommendationResponse> {

        return ResponseEntity(
                exerciseRecommendationService.getExerciseRecommendation(accessToken), HttpStatus.OK)
    }
}
