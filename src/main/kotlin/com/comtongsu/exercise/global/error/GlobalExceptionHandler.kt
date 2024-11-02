package com.comtongsu.exercise.global.error

import com.comtongsu.exercise.global.error.exception.BusinessException
import com.comtongsu.exercise.global.error.exception.EntityNotFoundException
import com.comtongsu.exercise.global.error.exception.ExternalApiException
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

    @ExceptionHandler(ExternalApiException::class)
    protected fun handleExternalApiException(
            exception: ExternalApiException
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
}
