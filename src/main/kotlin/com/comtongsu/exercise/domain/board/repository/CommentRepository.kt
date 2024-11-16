package com.comtongsu.exercise.domain.board.repository

import com.comtongsu.exercise.domain.board.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>
