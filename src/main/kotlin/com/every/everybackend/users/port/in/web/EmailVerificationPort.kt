package com.every.everybackend.users.port.`in`.web

import com.every.everybackend.users.service.command.EmailVerificationCommand

interface EmailVerificationPort {

  fun verifyEmail(command: EmailVerificationCommand): Boolean

}
