package com.every.everybackend.users.domain

import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import java.time.LocalDateTime

data class User(
  val id: Long,
  val email: String,
  var password: String,
  var name: String,
  var image: String,
  val role: UserRole,
  val provider: UserProvider,
  val providerId: String?,
  val createdAt: LocalDateTime?,
  val updatedAt: LocalDateTime?
)
