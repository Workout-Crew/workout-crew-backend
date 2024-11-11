package com.comtongsu.exercise.domain.exerciseRecommendation.repository

import com.comtongsu.exercise.domain.exerciseRecommendation.entity.ExerciseRecommendation
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRecommendationRepository : JpaRepository<ExerciseRecommendation, Long>
