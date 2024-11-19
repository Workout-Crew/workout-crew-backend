package com.comtongsu.exercise.domain.board.dto.response

import java.time.LocalDateTime

class BoardResponseDto {
    data class CategoryListResponse(val categoryList: List<CategoryContent>)

    data class CategoryContent(
            val id: Long?,
            val name: String?,
            val description: String?,
    )

    data class BoardListResponse(val boardList: List<BoardContent>)

    data class BoardContent(
            val id: Long?,
            val title: String?,
            val content: String?,
            val imageList: List<String>?,
            val writer: String?,
            val commentCount: Int?,
            val createdDate: LocalDateTime?,
    )

    data class BoardDetailResponse(
            val boardContent: BoardContent,
            val commentList: List<CommentContent>,
    )

    data class CommentContent(
            val commentId: Long?,
            val content: String?,
            val writer: String?,
            val createdDate: LocalDateTime?,
    )
}
