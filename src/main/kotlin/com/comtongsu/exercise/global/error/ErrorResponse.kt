package com.comtongsu.exercise.global.error

data class ErrorResponse(
        val code: String,
        val message: String,
        val status: Int,
) {
    constructor(
            errorCode: ErrorCode
    ) : this(
            message = errorCode.message,
            status = errorCode.status,
            code = errorCode.code,
    )
}
