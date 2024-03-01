package com.every.everybackend.users.service.command

data class SignupCommand(
  val email: String,
  val password: String,
  val name: String,
  val image: String
)
