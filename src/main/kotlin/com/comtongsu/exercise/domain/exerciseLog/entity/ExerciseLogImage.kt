package com.comtongsu.exercise.domain.exerciseLog.entity

import jakarta.persistence.*

@Entity
@Table(name = "exercise_log_image")
class ExerciseLogImage(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "exercise_log_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var exerciseLog: ExerciseLog? = null,
        @Column(name = "image_url") var imageUrl: String? = null,
) {
    companion object {
        fun createExerciseLogImage(exerciseLog: ExerciseLog, imageUrl: String): ExerciseLogImage {
            return ExerciseLogImage(exerciseLog = exerciseLog, imageUrl = imageUrl)
        }
    }
}
