package com.comtongsu.exercise.domain.board.entity

import com.comtongsu.exercise.domain.board.dto.request.BoardRequestDto
import com.comtongsu.exercise.domain.board.dto.response.BoardResponseDto
import jakarta.persistence.*

@Entity
@Table(name = "category")
class Category(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        var name: String? = null,
        var description: String? = null,
        @OneToMany(mappedBy = "category") var boardList: MutableList<Board> = mutableListOf(),
) {
    companion object {
        fun createCategory(request: BoardRequestDto.CategoryRequest): Category {
            return Category(name = request.name, description = request.description)
        }
    }

    fun toCategoryContent(): BoardResponseDto.CategoryContent {
        return BoardResponseDto.CategoryContent(id = id, name = name, description = description)
    }
}
