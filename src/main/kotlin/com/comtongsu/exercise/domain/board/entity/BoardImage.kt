package com.comtongsu.exercise.domain.board.entity

import jakarta.persistence.*

@Entity
@Table(name = "board_image")
class BoardImage(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "board_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var board: Board? = null,
        @Column(name = "image_url") var imageUrl: String? = null,
) {
    companion object {
        fun createBoardImage(board: Board, imageUrl: String): BoardImage {
            return BoardImage(board = board, imageUrl = imageUrl)
        }
    }
}
