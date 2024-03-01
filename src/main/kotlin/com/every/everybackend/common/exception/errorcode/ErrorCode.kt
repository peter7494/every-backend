package com.every.everybackend.common.exception.errorcode

import org.springframework.http.HttpStatus

interface ErrorCode {

  fun getStatus(): HttpStatus
  fun getCode(): String
  fun getMessage(): String
}