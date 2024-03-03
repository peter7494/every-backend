package com.every.everybackend.users.service

import com.every.everybackend.users.domain.User
import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import com.every.everybackend.users.domain.enums.UserStatus
import com.every.everybackend.users.port.`in`.web.SignupPort
import com.every.everybackend.users.port.out.mail.SendVerifyCodePort
import com.every.everybackend.users.port.out.persistence.SignupPersistencePort
import com.every.everybackend.users.service.command.SignupCommand
import com.every.everybackend.users.util.GenerateCode
import org.springframework.stereotype.Service

@Service
class SignupService(
  private val signupPersistencePort: SignupPersistencePort,
  private val sendVerifyCodePort: SendVerifyCodePort,
  private val generateCode: GenerateCode
): SignupPort {

  override fun signup(command: SignupCommand): User {

    val code = generateCode.generateCode(6)

    val user = User(
      email = command.email,
      password = command.password,
      name = command.name,
      image = command.image,
      role = UserRole.USER,
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED,
      verifyCode = code
    )

    val createUser = signupPersistencePort.createUser(user)

    sendVerifyCodePort.sendVerifyCode(createUser.email, code)

    return createUser
  }
}