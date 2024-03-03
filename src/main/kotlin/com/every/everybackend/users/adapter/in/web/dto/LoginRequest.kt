package com.every.everybackend.users.adapter.`in`.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class LoginRequest(
  @field:NotBlank
  @field:Email
  val email: String,
  @field:Length(min = 6)
  val password: String
)
