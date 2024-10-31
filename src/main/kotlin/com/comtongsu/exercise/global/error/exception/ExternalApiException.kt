package com.comtongsu.exercise.global.error.exception

import com.comtongsu.exercise.global.error.ErrorCode

open class ExternalApiException : BusinessException {
    constructor(errorCode: ErrorCode) : super(errorCode)
}
