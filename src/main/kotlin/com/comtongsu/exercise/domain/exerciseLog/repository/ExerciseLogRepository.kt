package com.comtongsu.exercise.domain.exerciseLog.repository

import com.comtongsu.exercise.domain.exerciseLog.entity.ExerciseLog
import com.comtongsu.exercise.domain.gathering.entity.Gathering
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseLogRepository : JpaRepository<ExerciseLog, Long> {
    fun existsByGathering(gathering: Gathering): Boolean
}
