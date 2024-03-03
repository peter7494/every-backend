package com.every.everybackend.users.adapter.out.persistence

import com.every.everybackend.users.adapter.out.persistence.entity.UserEntity
import com.every.everybackend.users.adapter.out.persistence.repository.UserRepository
import com.every.everybackend.users.domain.User
import com.every.everybackend.users.port.out.persistence.RegisterUserPersistencePort
import org.springframework.stereotype.Component

@Component
class RegisterUserPersistenceAdapter(
  private val userRepository: UserRepository
): RegisterUserPersistencePort {
  override fun registerUser(user: User): User {

    val userEntity = UserEntity(
      email = user.email,
      password = user.password,
      name = user.name,
      image = user.image,
      role = user.role,
      provider = user.provider,
      status = user.status,
      providerId = user.providerId,
    ).let {
      userRepository.save(it)
    }

    return User(
      id = userEntity.id,
      email = userEntity.email,
      password = userEntity.password,
      name = userEntity.name,
      image = userEntity.image,
      role = userEntity.role,
      provider = userEntity.provider,
      status = userEntity.status,
      providerId = userEntity.providerId,
      createdAt = userEntity.createdAt,
      updatedAt = userEntity.updatedAt
    )
  }
}