package com.comtongsu.exercise.domain.exerciseLog.repository

import com.comtongsu.exercise.domain.exerciseLog.entity.ExerciseLog
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseLogRepository : JpaRepository<ExerciseLog, Long>
