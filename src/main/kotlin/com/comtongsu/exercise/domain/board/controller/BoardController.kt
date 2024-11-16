package com.comtongsu.exercise.domain.board.controller

import com.comtongsu.exercise.domain.board.dto.request.BoardRequestDto
import com.comtongsu.exercise.domain.board.dto.response.BoardResponseDto
import com.comtongsu.exercise.domain.board.service.BoardService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/board")
@Tag(name = "Board", description = "게시판 API")
class BoardController(private val boardService: BoardService) {

    @Operation(summary = "게시글 생성")
    @PostMapping
    fun createBoard(
            @RequestHeader token: String,
            @RequestPart(value = "image") imageList: List<MultipartFile>?,
            @RequestBody request: BoardRequestDto.BoardRequest
    ): ResponseEntity<Unit> {
        boardService.createBoard(token, imageList, request)

        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @Operation(summary = "게시판 개설")
    @PostMapping("/category")
    fun createCategory(
            @RequestHeader token: String,
            @RequestBody request: BoardRequestDto.CategoryRequest
    ): ResponseEntity<Unit> {
        boardService.createCategory(request)

        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @Operation(summary = "게시판 목록 조회")
    @GetMapping("/category")
    fun getCategoryList(): ResponseEntity<List<BoardResponseDto.CategoryListResponse>> {
        return ResponseEntity(boardService.getCategoryList(), HttpStatus.OK)
    }
}
