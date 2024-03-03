package com.every.everybackend.users.port.out.persistence

import com.every.everybackend.users.domain.User

interface SignupPersistencePort {

  fun createUser(user: User): User
}
