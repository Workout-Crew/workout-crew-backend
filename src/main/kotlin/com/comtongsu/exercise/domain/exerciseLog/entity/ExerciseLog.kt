package com.comtongsu.exercise.domain.exerciseLog.entity

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.global.common.BaseEntity
import com.comtongsu.exercise.global.enums.ExerciseType
import jakarta.persistence.*

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
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "account_id",
                nullable = false,
                foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var account: Account? = null,
        @OneToMany(mappedBy = "exerciseLog")
        var imageList: MutableList<ExerciseLogImage> = mutableListOf(),
) : BaseEntity()
