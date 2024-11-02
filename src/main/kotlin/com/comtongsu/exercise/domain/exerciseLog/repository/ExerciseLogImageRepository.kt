package com.comtongsu.exercise.domain.exerciseLog.repository

import com.comtongsu.exercise.domain.exerciseLog.entity.ExerciseLogImage
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseLogImageRepository : JpaRepository<ExerciseLogImage, Long>
