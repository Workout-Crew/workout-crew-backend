package com.comtongsu.exercise.domain.medal.entity

import com.comtongsu.exercise.domain.account.entity.Account
import jakarta.persistence.*

@Entity
@Table(name = "account_medal")
class AccountMedal(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "account_id",
                nullable = false,
                foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var account: Account? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "medal_id", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var medal: Medal? = null,
)
