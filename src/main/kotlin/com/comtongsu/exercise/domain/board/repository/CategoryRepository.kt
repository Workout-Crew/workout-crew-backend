package com.comtongsu.exercise.domain.board.repository

import com.comtongsu.exercise.domain.board.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByNameContaining(keyword: String): List<Category>
}
