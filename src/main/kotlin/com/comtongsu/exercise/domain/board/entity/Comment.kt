package com.comtongsu.exercise.domain.board.entity

import com.comtongsu.exercise.domain.account.entity.Account
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        var content: String? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "board_id", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var board: Board? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "account_id",
                nullable = false,
                foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var account: Account? = null,
        var writer: Boolean? = null,
)
