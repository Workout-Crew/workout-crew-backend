package com.comtongsu.exercise.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(val code: String, val message: String, var status: Int) {
    // Account
    ACCOUNT_NOT_FOUND("A01", "Account is not found.", HttpStatus.BAD_REQUEST.value()),

    // Exercise Recommendation
    EXERCISE_RECOMMENDATION_NOT_FOUND(
            "ER01", "Exercise Recommendation is not found.", HttpStatus.BAD_REQUEST.value()),

    // S3
    EMPTY_IMAGE("S01", "Image is empty.", HttpStatus.BAD_REQUEST.value()),
    NOT_VALIDATE_FILE("S02", "File is not validated.", HttpStatus.BAD_REQUEST.value()),
    PUT_IMAGE("S03", "Failed to put image.", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    // Gathering
    GATHERING_NOT_FOUND("G01", "Gathering is not found.", HttpStatus.BAD_REQUEST.value()),
    LEADER_NOT_FOUND("G02", "Leader is not found.", HttpStatus.BAD_REQUEST.value()),
    ALREADY_JOINED_GATHERING("G03", "Already joined gathering.", HttpStatus.BAD_REQUEST.value()),

    // Bedrock
    INVOKE_ERROR("BR01", "Failed to invoke bedrock.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
    ENUM_NOT_MATCHED("BR02", "Enum is not matched.", HttpStatus.BAD_REQUEST.value()),

    // Batch
    EXECUTE_ERROR("BA01", "Failed to execute batch.", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    // Board
    CATEGORY_NOT_FOUND("B01", "Category is not found.", HttpStatus.BAD_REQUEST.value()),
    BOARD_NOT_FOUND("B02", "Board is not found.", HttpStatus.BAD_REQUEST.value()),
}
