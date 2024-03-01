package com.every.everybackend.common.exception.dto

import com.every.everybackend.common.exception.errorcode.ErrorCode
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponse(
  val status: HttpStatus,
  val code: String,
  val message: String,
  val timestamp: LocalDateTime = LocalDateTime.now()
) {

  constructor(errorCode: ErrorCode) : this(
    errorCode.getStatus(),
    errorCode.getCode(),
    errorCode.getMessage()
  )


  constructor(errorCode: ErrorCode, message: String) : this(
    errorCode.getStatus(),
    errorCode.getCode(),
    message
  )

  constructor(message: String): this(
    HttpStatus.INTERNAL_SERVER_ERROR,
    "E000",
    message
  )
}


