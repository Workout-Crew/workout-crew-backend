package com.comtongsu.exercise.global.error

import com.comtongsu.exercise.global.error.exception.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(
            exception: BusinessException
    ): ResponseEntity<ErrorResponse> {
        logger.error(exception.errorCode.code, exception)
        val errorCode = exception.errorCode
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(EntityNotFoundException::class)
    protected fun handleEntityNotFoundException(
            exception: EntityNotFoundException
    ): ResponseEntity<ErrorResponse> {
        logger.error(exception.errorCode.code, exception)
        val errorCode = exception.errorCode
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(S3Exception::class)
    protected fun handleS3FoundException(exception: S3Exception): ResponseEntity<ErrorResponse> {
        logger.error(exception.errorCode.code, exception)
        val errorCode = exception.errorCode
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(BedrockException::class)
    protected fun handleBedrockFoundException(
            exception: BedrockException
    ): ResponseEntity<ErrorResponse> {
        logger.error(exception.errorCode.code, exception)
        val errorCode = exception.errorCode
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.valueOf(errorCode.status))
    }
}
