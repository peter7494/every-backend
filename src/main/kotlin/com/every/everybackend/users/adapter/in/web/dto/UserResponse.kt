package com.every.everybackend.users.adapter.`in`.web.dto

data class UserResponse(
  val id: Long,
  val email: String,
  val name: String,
  val image: String,
  val status: String
)
