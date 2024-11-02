package com.comtongsu.exercise.global.error.exception

import com.comtongsu.exercise.global.error.ErrorCode

open class KakaoApiException : RuntimeException {
    val errorCode: ErrorCode

    constructor(errorCode: ErrorCode) : super(errorCode.message) {
        this.errorCode = errorCode
    }
}
