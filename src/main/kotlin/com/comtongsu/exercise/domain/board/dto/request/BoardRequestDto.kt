package com.comtongsu.exercise.domain.board.dto.request

class BoardRequestDto {
    data class BoardRequest(
            val title: String,
            val content: String,
            val categoryId: Long,
    )

    data class CategoryRequest(
            val name: String,
            val description: String,
    )

    data class CommentRequest(
            val boardId: Long,
            val content: String,
    )
}
