package com.every.everybackend.common.exception

import com.every.everybackend.common.exception.dto.ErrorResponse
import com.every.everybackend.common.exception.errorcode.UserErrorCode

class ApiException(
  val errorResponse: ErrorResponse
): RuntimeException() {

  constructor(errorCode: UserErrorCode): this(ErrorResponse(errorCode))

  constructor(errorCode: UserErrorCode, message: String): this(ErrorResponse(errorCode, message))

  constructor(message: String): this(ErrorResponse(message))
}