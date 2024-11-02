package com.comtongsu.exercise.global.error.exception

import com.comtongsu.exercise.global.error.ErrorCode

open class EntityNotFoundException : BusinessException {
    constructor(errorCode: ErrorCode) : super(errorCode)
}
