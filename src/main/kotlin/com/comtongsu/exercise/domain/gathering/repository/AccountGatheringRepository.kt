package com.comtongsu.exercise.domain.gathering.repository

import com.comtongsu.exercise.domain.gathering.entity.AccountGathering
import com.comtongsu.exercise.domain.gathering.entity.Gathering
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountGatheringRepository : JpaRepository<AccountGathering, Long> {
    fun findByGathering(gathering: Gathering): List<AccountGathering>
}
