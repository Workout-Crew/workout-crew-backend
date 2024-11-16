package com.comtongsu.exercise.domain.board.repository

import com.comtongsu.exercise.domain.board.entity.BoardImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository interface BoardImageRepository : JpaRepository<BoardImage, Long>
