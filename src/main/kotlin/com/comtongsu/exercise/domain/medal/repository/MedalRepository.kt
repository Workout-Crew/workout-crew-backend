package com.comtongsu.exercise.domain.medal.repository

import com.comtongsu.exercise.domain.medal.entity.Medal
import com.comtongsu.exercise.domain.medal.entity.enums.MedalType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MedalRepository : JpaRepository<Medal, Long> {
    fun findByMedalType(medalType: MedalType): List<Medal>
}
