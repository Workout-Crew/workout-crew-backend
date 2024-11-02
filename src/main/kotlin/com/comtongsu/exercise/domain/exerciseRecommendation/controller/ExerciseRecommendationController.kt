package com.comtongsu.exercise.domain.exerciseRecommendation.controller

import com.comtongsu.exercise.domain.exerciseRecommendation.dto.ExerciseRecommendationResponseDto
import com.comtongsu.exercise.domain.exerciseRecommendation.service.ExerciseRecommendationService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/exerciserecommendation")
@Tag(name = "Exercise Recommendation", description = "EXERCISE RECOMMENDATION API")
class ExerciseRecommendationController(
        private val exerciseRecommendationService: ExerciseRecommendationService
) {
    @GetMapping
    fun getExerciseRecommendation(
            @RequestHeader accessToken: String
    ): ResponseEntity<ExerciseRecommendationResponseDto.ExerciseRecommendationResponse> {

        return ResponseEntity(
                exerciseRecommendationService.getExerciseRecommendation(accessToken), HttpStatus.OK)
    }
}
