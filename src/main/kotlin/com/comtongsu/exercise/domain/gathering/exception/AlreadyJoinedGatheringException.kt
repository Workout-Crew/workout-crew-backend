package com.comtongsu.exercise.domain.gathering.exception

import com.comtongsu.exercise.global.error.ErrorCode
import com.comtongsu.exercise.global.error.exception.BusinessException

class AlreadyJoinedGatheringException : BusinessException(ErrorCode.ALREADY_JOINED_GATHERING)
