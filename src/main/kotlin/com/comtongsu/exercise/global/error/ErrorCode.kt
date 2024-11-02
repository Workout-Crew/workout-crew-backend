package com.comtongsu.exercise.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(val code: String, val message: String, var status: Int) {
    // Account
    KAKAO_ACCOUNT_NOT_FOUND("A01", "Account is not found in kakao.", HttpStatus.BAD_REQUEST.value()),

    // Exercise Recommendation
    EXERCISE_RECOMMENDATION_NOT_FOUND("ER01", "Exercise Recommendation is not found.", HttpStatus.BAD_REQUEST.value())
}
