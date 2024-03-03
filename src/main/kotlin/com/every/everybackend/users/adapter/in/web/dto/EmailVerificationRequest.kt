package com.every.everybackend.users.adapter.`in`.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class EmailVerificationRequest(
  @Email
  val email: String,
  @NotBlank
  @Length(min = 6, max = 6)
  val code: String
)
