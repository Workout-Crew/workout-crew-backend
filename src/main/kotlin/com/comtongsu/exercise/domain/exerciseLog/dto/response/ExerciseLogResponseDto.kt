package com.comtongsu.exercise.domain.exerciseLog.dto.response

import com.comtongsu.exercise.global.enums.ExerciseType
import java.time.LocalDateTime

class ExerciseLogResponseDto {
    data class TotalExerciseLogListResponse(val totalExerciseLogList: List<TotalExerciseLog>)

    data class TotalExerciseLog(
            val exerciseType: ExerciseType,
            val day: Int,
            val hour: Int,
    )

    data class ExerciseLogByDateListResponse(val exerciseLogByDateList: List<ExerciseLogByDate>)

    data class ExerciseLogByDate(
            val title: String?,
            val exerciseType: ExerciseType?,
            val description: String?,
            val intensity: Int?,
            val startTime: LocalDateTime?,
            val endTime: LocalDateTime?,
            val imageList: List<String>?,
            val gatheringTitle: String?,
    )
}
