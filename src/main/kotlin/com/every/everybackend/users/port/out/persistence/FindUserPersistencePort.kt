package com.every.everybackend.users.port.out.persistence

import com.every.everybackend.users.domain.User

interface FindUserPersistencePort {
  fun findByEmail(email: String): User?
}