package com.every.everybackend.users.adapter.out.persistence

import com.every.everybackend.users.adapter.out.persistence.repository.UserRepository
import com.every.everybackend.users.domain.User
import com.every.everybackend.users.port.out.persistence.FindUserPersistencePort
import org.springframework.stereotype.Component

@Component
class FindUserPersistenceAdapter(
  private val userRepository: UserRepository
): FindUserPersistencePort {
  override fun findByEmail(email: String): User? {
    return userRepository.findByEmail(email)?.let {
      return User(
        id = it.id,
        email = it.email,
        password = it.password,
        name = it.name,
        image = it.image,
        role = it.role,
        provider = it.provider,
        status = it.status,
        providerId = it.providerId,
        createdAt = it.createdAt,
        updatedAt = it.updatedAt
      )
    }
  }
}