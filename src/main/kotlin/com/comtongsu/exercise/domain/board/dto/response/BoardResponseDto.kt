package com.comtongsu.exercise.domain.board.dto.response

class BoardResponseDto {
    data class CategoryListResponse(val categoryList: List<CategoryContent>)

    data class CategoryContent(
            val id: Long?,
            val name: String?,
            val description: String?,
    )
}
