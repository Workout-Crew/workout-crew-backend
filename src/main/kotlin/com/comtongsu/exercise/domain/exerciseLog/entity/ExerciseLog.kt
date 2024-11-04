package com.comtongsu.exercise.domain.exerciseLog.entity

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.exerciseLog.dto.request.ExerciseLogRequestDto
import com.comtongsu.exercise.global.common.BaseEntity
import com.comtongsu.exercise.global.enums.ExerciseType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "exercise_log")
class ExerciseLog(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        var title: String? = null,
        @Enumerated(EnumType.STRING)
        @Column(name = "exercise_type")
        var exerciseType: ExerciseType? = null,
        var description: String? = null,
        var intensity: Int? = null,
        @Column(name = "start_time") var startTime: LocalDateTime? = null,
        @Column(name = "end_time") var endTime: LocalDateTime? = null,
        var exerciseTime: Int? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "account_id",
                nullable = false,
                foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var account: Account? = null,
        @OneToMany(mappedBy = "exerciseLog")
        var imageList: MutableList<ExerciseLogImage> = mutableListOf(),
) : BaseEntity() {
    companion object {
        fun createExerciseLog(
                account: Account,
                exerciseTime: Int,
                request: ExerciseLogRequestDto.ExerciseLogRequest,
        ): ExerciseLog {
            return ExerciseLog(
                    title = request.title,
                    exerciseType = request.exerciseType,
                    description = request.description,
                    intensity = request.intensity,
                    startTime = request.startTime,
                    endTime = request.endTime,
                    exerciseTime = exerciseTime,
                    account = account)
        }
    }
}
