package com.comtongsu.exercise.domain.board.exception

import com.comtongsu.exercise.global.error.ErrorCode
import com.comtongsu.exercise.global.error.exception.EntityNotFoundException

class CategoryNotFoundException : EntityNotFoundException(ErrorCode.CATEGORY_NOT_FOUND)
