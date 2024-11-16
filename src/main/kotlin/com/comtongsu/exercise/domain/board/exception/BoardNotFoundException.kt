package com.comtongsu.exercise.domain.board.exception

import com.comtongsu.exercise.global.error.ErrorCode
import com.comtongsu.exercise.global.error.exception.EntityNotFoundException

class BoardNotFoundException : EntityNotFoundException(ErrorCode.BOARD_NOT_FOUND)
