package com.comtongsu.exercise.domain.board.dto.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class BoardRequestDto {
    data class BoardRequest
    @JsonCreator
    constructor(
            @JsonProperty("title") val title: String,
            @JsonProperty("content") val content: String,
            @JsonProperty("categoryId") val categoryId: Long,
    )

    data class CategoryRequest
    @JsonCreator
    constructor(
            @JsonProperty("name") val name: String,
            @JsonProperty("description") val description: String,
    )

    data class CommentRequest
    @JsonCreator
    constructor(
            @JsonProperty("boardId") val boardId: Long,
            @JsonProperty("content") val content: String,
    )
}
