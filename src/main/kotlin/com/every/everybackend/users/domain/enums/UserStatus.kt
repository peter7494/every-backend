package com.every.everybackend.users.domain.enums

enum class UserStatus(
  val value: String
) {
  ACTIVE("ACTIVE"),
  UNVERIFIED("UNVERIFIED"),
  DELETED("DELETED")
}
