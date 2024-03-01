package com.every.everybackend.common.exception.errorcode

import org.springframework.http.HttpStatus

enum class CommonErrorCode(
  private val status: HttpStatus,
  private val code: String,
  private val message: String
): ErrorCode {

  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E000", "서버에서 에러가 발생했습니다");

  override fun getStatus(): HttpStatus {
    return status
  }

  override fun getCode(): String {
    return code
  }

  override fun getMessage(): String {
    return message
  }
}