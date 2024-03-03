package com.every.everybackend.users.domain

import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import com.every.everybackend.users.domain.enums.UserStatus
import java.time.LocalDateTime

data class User(
  val id: Long? = null,
  val email: String,
  var password: String,
  var name: String,
  var image: String? = null,
  val role: UserRole,
  val provider: UserProvider,
  val status: UserStatus,
  val verifyCode: String? = null,
  val providerId: String? = null,
  val createdAt: LocalDateTime? = null,
  val updatedAt: LocalDateTime? = null
)
