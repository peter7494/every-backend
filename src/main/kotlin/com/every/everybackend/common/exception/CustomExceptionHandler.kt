package com.every.everybackend.common.exception

import com.every.everybackend.common.exception.dto.ErrorResponse
import com.every.everybackend.common.exception.errorcode.CommonErrorCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler(
  private val log: Logger? = LoggerFactory.getLogger(CustomExceptionHandler::class.java)
) {

  @ExceptionHandler(ApiException::class)
  fun handleApiException(e: ApiException): ResponseEntity<ErrorResponse> {
    log?.error("ApiException: ${e.message}")
    return ResponseEntity(
      e.errorResponse,
      e.errorResponse.status
    )
  }

  @ExceptionHandler(Exception::class)
  fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
    log?.error("Exception: ${e.message}")
    return ResponseEntity(
      ErrorResponse(CommonErrorCode.INTERNAL_SERVER_ERROR),
      HttpStatus.INTERNAL_SERVER_ERROR
    )
  }
}