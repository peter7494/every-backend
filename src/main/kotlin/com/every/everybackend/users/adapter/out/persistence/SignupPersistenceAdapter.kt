package com.every.everybackend.users.adapter.out.persistence

import com.every.everybackend.users.adapter.out.persistence.entity.UserEntity
import com.every.everybackend.users.adapter.out.persistence.repository.UserRepository
import com.every.everybackend.users.domain.User
import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import com.every.everybackend.users.domain.enums.UserStatus
import com.every.everybackend.users.port.out.persistence.SignupPersistencePort
import org.springframework.stereotype.Component

@Component
class SignupPersistenceAdapter(
  private val userRepository: UserRepository
): SignupPersistencePort {

  override fun signup(user: User): User {

    userRepository.findByEmail(user.email)?.let {
      throw IllegalArgumentException("이미 가입된 이메일입니다.")
    }

    val userEntity = UserEntity(
      email = user.email,
      password = user.password,
      name = user.name,
      image = user.image,
      role = UserRole.USER,
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED
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