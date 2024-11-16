package com.comtongsu.exercise.domain.board.entity

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.board.dto.request.BoardRequestDto
import com.comtongsu.exercise.global.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "board")
class Board(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        var title: String? = null,
        var content: String? = null,
        @Column(name = "like_count") var likeCount: Int = 0,
        @Column(name = "view_count") var viewCount: Int = 0,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var category: Category? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var account: Account? = null,
        @OneToMany(mappedBy = "board") var commentList: MutableList<Comment> = mutableListOf(),
        @OneToMany(mappedBy = "board") var imageList: MutableList<BoardImage> = mutableListOf(),
) : BaseEntity() {
    companion object {
        fun createBoard(
                account: Account,
                category: Category,
                request: BoardRequestDto.BoardRequest
        ): Board {
            return Board(
                    account = account,
                    category = category,
                    title = request.title,
                    content = request.content,
            )
        }
    }
}
