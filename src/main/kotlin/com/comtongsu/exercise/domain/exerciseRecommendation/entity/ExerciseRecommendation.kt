package com.comtongsu.exercise.domain.exerciseRecommendation.entity

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.global.common.BaseEntity
import com.comtongsu.exercise.global.enums.ExerciseType
import jakarta.persistence.*

@Entity
@Table(name = "exercise_recommendation")
class ExerciseRecommendation(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        @Enumerated(EnumType.STRING) var exerciseType: ExerciseType? = null,
        var intensity: Int? = null,
        var description: String? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "account_id",
                nullable = false,
                foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var account: Account? = null,
) : BaseEntity() {
    companion object {
        fun createExerciseRecommendation(
                account: Account,
                exerciseType: ExerciseType,
                description: String,
        ): ExerciseRecommendation {
            return ExerciseRecommendation(
                    account = account,
                    exerciseType = exerciseType,
                    description = description,
            )
        }
    }
}
