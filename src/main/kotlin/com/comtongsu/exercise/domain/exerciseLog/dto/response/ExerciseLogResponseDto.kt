package com.comtongsu.exercise.domain.exerciseLog.dto.response

import com.comtongsu.exercise.global.enums.ExerciseType

class ExerciseLogResponseDto {
    data class TotalExerciseLogListResponse(val currentExerciseLogList: List<TotalExerciseLog>)

    data class TotalExerciseLog(
            val exerciseType: ExerciseType,
            val day: Int,
            val hour: Int,
    )
}
