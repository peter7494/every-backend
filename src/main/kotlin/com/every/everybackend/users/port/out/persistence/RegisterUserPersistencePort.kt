package com.every.everybackend.users.port.out.persistence

import com.every.everybackend.users.domain.User

interface RegisterUserPersistencePort {
  fun registerUser(user:User): User
}