package com.comtongsu.exercise.domain.board.entity

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.board.dto.response.BoardResponseDto
import com.comtongsu.exercise.global.common.BaseEntity
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
        var account: Account = Account(),
) : BaseEntity() {
    companion object {
        fun createComment(
                content: String,
                board: Board,
                account: Account,
        ): Comment {
            return Comment(
                    content = content,
                    board = board,
                    account = account,
            )
        }
    }

    fun toCommentContent(): BoardResponseDto.CommentContent {
        return BoardResponseDto.CommentContent(
                content = content,
                writer = account.nickname,
                createdDate = createdDate,
        )
    }
}
