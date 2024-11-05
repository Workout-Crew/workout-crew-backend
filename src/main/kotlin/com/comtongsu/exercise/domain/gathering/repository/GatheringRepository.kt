package com.comtongsu.exercise.domain.gathering.repository

import com.comtongsu.exercise.domain.gathering.entity.Gathering
import org.springframework.data.jpa.repository.JpaRepository

interface GatheringRepository : JpaRepository<Gathering, Long>
