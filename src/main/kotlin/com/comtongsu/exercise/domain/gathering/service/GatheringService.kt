package com.comtongsu.exercise.domain.gathering.service

import com.comtongsu.exercise.domain.gathering.entity.Gathering
import com.comtongsu.exercise.domain.gathering.exception.GatheringNotFoundException
import com.comtongsu.exercise.domain.gathering.repository.GatheringRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GatheringService(private val gatheringRepository: GatheringRepository) {
    fun getGatheringById(gatheringId: Long): Gathering {
        return gatheringRepository.findByIdOrNull(gatheringId) ?: throw GatheringNotFoundException()
    }
}
