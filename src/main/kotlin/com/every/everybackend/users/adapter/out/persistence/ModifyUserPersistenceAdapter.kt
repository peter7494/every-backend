package com.every.everybackend.users.adapter.out.persistence

import com.every.everybackend.common.exception.ApiException
import com.every.everybackend.common.exception.errorcode.UserErrorCode
import com.every.everybackend.users.adapter.out.persistence.repository.UserRepository
import com.every.everybackend.users.domain.User
import com.every.everybackend.users.domain.enums.UserStatus
import com.every.everybackend.users.port.out.persistence.ModifyUserPersistencePort
import org.springframework.stereotype.Component

@Component
class ModifyUserPersistenceAdapter(
  private val userRepository: UserRepository
): ModifyUserPersistencePort {
  override fun modifyUserStatus(email: String, status: UserStatus): User {

    val userEntity = userRepository.findByEmail(email)?.let {
      it.status = status
      userRepository.save(it)
    } ?: throw ApiException(UserErrorCode.USER_NOT_FOUND)

    return User(
      id = userEntity.id,
      email = userEntity.email,
      password = userEntity.password,
      name = userEntity.name,
      image = userEntity.image,
      role = userEntity.role,
      provider = userEntity.provider,
      status = userEntity.status,
      verifyCode = userEntity.verifyCode,
      providerId = userEntity.providerId,
      createdAt = userEntity.createdAt,
      updatedAt = userEntity.updatedAt
    )
  }

}