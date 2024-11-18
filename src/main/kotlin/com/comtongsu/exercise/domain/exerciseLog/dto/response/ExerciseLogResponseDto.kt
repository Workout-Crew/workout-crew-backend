package com.comtongsu.exercise.domain.exerciseLog.dto.response

import com.comtongsu.exercise.global.enums.ExerciseType
import java.io.Serializable
import java.time.LocalDateTime

class ExerciseLogResponseDto {
    data class TotalExerciseLogListResponse(val totalExerciseLogList: List<TotalExerciseLog>)

    data class TotalExerciseLog(
            val exerciseType: ExerciseType,
            val day: Long,
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

    data class ExerciseForRecommendation(
            val type: String?,
            val date: String?,
            val title: String?,
            val contents: List<ExerciseContent>
    ) : Serializable

    data class ExerciseContent(
            val type: String,
            val content: String?,
    )
}
