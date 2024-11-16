package com.comtongsu.exercise.domain.board.service

import com.comtongsu.exercise.domain.account.service.KakaoService
import com.comtongsu.exercise.domain.board.dto.request.BoardRequestDto
import com.comtongsu.exercise.domain.board.dto.response.BoardResponseDto
import com.comtongsu.exercise.domain.board.entity.Board
import com.comtongsu.exercise.domain.board.entity.BoardImage
import com.comtongsu.exercise.domain.board.entity.Category
import com.comtongsu.exercise.domain.board.exception.CategoryNotFoundException
import com.comtongsu.exercise.domain.board.repository.BoardImageRepository
import com.comtongsu.exercise.domain.board.repository.BoardRepository
import com.comtongsu.exercise.domain.board.repository.CategoryRepository
import com.comtongsu.exercise.global.s3.S3ImageService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional(readOnly = true)
class BoardService(
        private val kakaoService: KakaoService,
        private val s3ImageService: S3ImageService,
        private val boardRepository: BoardRepository,
        private val boardImageRepository: BoardImageRepository,
        private val categoryRepository: CategoryRepository
) {

    @Transactional
    fun createBoard(
            token: String,
            imageList: List<MultipartFile>?,
            request: BoardRequestDto.BoardRequest
    ) {
        val account = kakaoService.getAccount(token)
        val category =
                categoryRepository.findByIdOrNull(request.categoryId) ?: throw CategoryNotFoundException()

        val board = Board.createBoard(account, category, request)
        boardRepository.save(board)

        imageList?.forEach {
            val imageUrl: String = s3ImageService.uploadImageToS3(it)
            boardImageRepository.save(BoardImage.createBoardImage(board, imageUrl))
        }
    }

    @Transactional
    fun createCategory(request: BoardRequestDto.CategoryRequest) {
        categoryRepository.save(Category.createCategory(request))
    }

    fun getCategoryList(): BoardResponseDto.CategoryListResponse {
        return BoardResponseDto.CategoryListResponse(
                categoryRepository.findAll().map { it.toCategoryContent() })
    }
}
