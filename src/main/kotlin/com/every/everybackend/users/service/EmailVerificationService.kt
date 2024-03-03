package com.every.everybackend.users.service

import com.every.everybackend.users.port.`in`.web.EmailVerificationPort
import com.every.everybackend.users.service.command.EmailVerificationCommand
import org.springframework.stereotype.Service

@Service
class EmailVerificationService(
): EmailVerificationPort {
  override fun verifyEmail(command: EmailVerificationCommand): Boolean {
    TODO("Not yet implemented")
  }
}