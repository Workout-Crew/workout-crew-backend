package com.comtongsu.exercise.domain.board.controller

import com.comtongsu.exercise.domain.board.dto.request.BoardRequestDto
import com.comtongsu.exercise.domain.board.dto.response.BoardResponseDto
import com.comtongsu.exercise.domain.board.service.BoardService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/board")
@Tag(name = "Board", description = "게시판 API")
class BoardController(private val boardService: BoardService) {

    @Operation(summary = "게시글 생성")
    @PostMapping(
            consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createBoard(
            @RequestHeader token: String,
            @RequestPart(value = "image") imageList: List<MultipartFile>?,
            @RequestPart(value = "request") request: BoardRequestDto.BoardRequest
    ): ResponseEntity<Unit> {
        boardService.createBoard(token, imageList, request)

        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @Operation(summary = "카테고리별 게시글 목록 조회")
    @GetMapping("/list/{categoryId}")
    fun getBoardList(
            @PathVariable categoryId: Long
    ): ResponseEntity<BoardResponseDto.BoardListResponse> {
        return ResponseEntity(boardService.getBoardList(categoryId), HttpStatus.OK)
    }

    @Operation(summary = "게시글 상세 조회")
    @GetMapping("/detail/{boardId}")
    fun getBoardDetail(
            @PathVariable boardId: Long
    ): ResponseEntity<BoardResponseDto.BoardDetailResponse> {
        return ResponseEntity(boardService.getBoardDetail(boardId), HttpStatus.OK)
    }

    @Operation(summary = "내가 작성한 게시글 조회")
    @GetMapping("/me")
    fun getMyBoardList(
            @RequestHeader token: String
    ): ResponseEntity<BoardResponseDto.BoardListResponse> {
        return ResponseEntity(boardService.getMyBoardList(token), HttpStatus.OK)
    }

    @Operation(summary = "내가 작성한 댓글이 있는 글 조회")
    @GetMapping("/comment/me")
    fun getBoardListByMyComment(
            @RequestHeader token: String
    ): ResponseEntity<BoardResponseDto.BoardListResponse> {
        return ResponseEntity(boardService.getBoardListByMyComment(token), HttpStatus.OK)
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
    fun getCategoryList(): ResponseEntity<BoardResponseDto.CategoryListResponse> {
        return ResponseEntity(boardService.getCategoryList(), HttpStatus.OK)
    }

    @Operation(summary = "게시판 목록 검색")
    @GetMapping("/search/{keyword}")
    fun getCategoryistByKeyword(
            @PathVariable keyword: String
    ): ResponseEntity<BoardResponseDto.CategoryListResponse> {
        return ResponseEntity(boardService.getCategoryListByKeyword(keyword), HttpStatus.OK)
    }

    @Operation(summary = "댓글 달기")
    @PostMapping("/comment")
    fun createComment(
            @RequestHeader token: String,
            @RequestBody request: BoardRequestDto.CommentRequest
    ): ResponseEntity<Unit> {
        boardService.createComment(token, request)

        return ResponseEntity(Unit, HttpStatus.CREATED)
    }
}
