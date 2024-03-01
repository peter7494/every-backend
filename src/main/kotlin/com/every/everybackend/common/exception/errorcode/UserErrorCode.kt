package com.every.everybackend.common.exception.errorcode

import org.springframework.http.HttpStatus

enum class UserErrorCode(
  private val status: HttpStatus,
  private val code: String,
  private val message: String
): ErrorCode {

  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "사용자를 찾을 수 없습니다"),
  USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "U002", "이미 존재하는 사용자입니다");

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