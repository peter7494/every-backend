package com.every.everybackend.users.port.`in`.web

import com.every.everybackend.users.domain.User
import com.every.everybackend.users.service.command.SignupCommand

interface SignupPort {
  fun signup(command: SignupCommand): User
}