package com.every.everybackend.users.port.out.persistence

import com.every.everybackend.users.domain.User
import com.every.everybackend.users.domain.enums.UserStatus

interface ModifyUserPersistencePort {
  fun modifyUserStatus(email: String ,status: UserStatus): User
}