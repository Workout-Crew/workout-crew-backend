package com.comtongsu.exercise.global.error.exception

import com.comtongsu.exercise.global.error.ErrorCode

open class BusinessException : RuntimeException {
    val errorCode: ErrorCode

    constructor(errorCode: ErrorCode) : super(errorCode.message) {
        this.errorCode = errorCode
    }
}
