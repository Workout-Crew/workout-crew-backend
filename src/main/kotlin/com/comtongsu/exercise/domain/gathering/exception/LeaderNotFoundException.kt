package com.comtongsu.exercise.domain.gathering.exception

import com.comtongsu.exercise.global.error.ErrorCode
import com.comtongsu.exercise.global.error.exception.EntityNotFoundException

class LeaderNotFoundException : EntityNotFoundException(ErrorCode.LEADER_NOT_FOUND)
