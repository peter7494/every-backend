package com.every.everybackend.users.service.command

data class EmailVerificationCommand(
  val email: String,
  val code: String
)
