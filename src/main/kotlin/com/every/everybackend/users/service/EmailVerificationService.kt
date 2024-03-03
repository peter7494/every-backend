package com.every.everybackend.users.service

import com.every.everybackend.common.exception.ApiException
import com.every.everybackend.common.exception.errorcode.UserErrorCode
import com.every.everybackend.users.domain.enums.UserStatus
import com.every.everybackend.users.port.`in`.web.EmailVerificationPort
import com.every.everybackend.users.port.out.persistence.FindUserPersistencePort
import com.every.everybackend.users.port.out.persistence.ModifyUserPersistencePort
import com.every.everybackend.users.service.command.EmailVerificationCommand
import org.springframework.stereotype.Service

@Service
class EmailVerificationService(
  private val findUserPersistencePort: FindUserPersistencePort,
  private val modifyUserPersistencePort: ModifyUserPersistencePort
): EmailVerificationPort {
  override fun verifyEmail(command: EmailVerificationCommand): Boolean {

    val user = findUserPersistencePort.findByEmail(command.email)
      ?: throw ApiException(UserErrorCode.USER_NOT_FOUND)

    if (user.verifyCode != command.code) {
      return false
    }

    modifyUserPersistencePort.modifyUserStatus(command.email, UserStatus.ACTIVE)
    return true
  }
}