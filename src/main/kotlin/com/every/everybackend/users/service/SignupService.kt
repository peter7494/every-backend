package com.every.everybackend.users.service

import com.every.everybackend.users.domain.User
import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import com.every.everybackend.users.domain.enums.UserStatus
import com.every.everybackend.users.port.`in`.web.SignupPort
import com.every.everybackend.users.port.out.persistence.SignupPersistencePort
import com.every.everybackend.users.service.command.SignupCommand
import org.springframework.stereotype.Service

@Service
class SignupService(
  private val signupPersistencePort: SignupPersistencePort
): SignupPort {

  override fun signup(command: SignupCommand): User {

    val user = User(
      email = command.email,
      password = command.password,
      name = command.name,
      image = command.image,
      role = UserRole.USER,
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED,
    )

    return signupPersistencePort.signup(user)
  }
}