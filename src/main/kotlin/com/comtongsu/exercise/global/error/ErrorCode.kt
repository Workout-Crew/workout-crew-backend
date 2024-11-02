package com.comtongsu.exercise.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(val code: String, val message: String, var status: Int) {
    // Account
    KAKAO_ACCOUNT_NOT_FOUND("A01", "Account is not found in kakao.", HttpStatus.BAD_REQUEST.value()),

    // Exercise Recommendation
    EXERCISE_RECOMMENDATION_NOT_FOUND(
            "ER01", "Exercise Recommendation is not found.", HttpStatus.BAD_REQUEST.value()),

    // S3
    EMPTY_IMAGE("S01", "Image is empty.", HttpStatus.BAD_REQUEST.value()),
    NOT_VALIDATE_FILE("S02", "File is not validated.", HttpStatus.BAD_REQUEST.value()),
    PUT_IMAGE("S03", "Failed to put image.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
}
