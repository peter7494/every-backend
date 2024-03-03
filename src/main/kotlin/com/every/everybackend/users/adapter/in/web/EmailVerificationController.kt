package com.every.everybackend.users.adapter.`in`.web

import com.every.everybackend.users.adapter.`in`.web.dto.EmailVerificationRequest
import com.every.everybackend.users.port.`in`.web.EmailVerificationPort
import com.every.everybackend.users.service.command.EmailVerificationCommand
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/email-verification")
class EmailVerificationController(
  private val emailVerificationPort: EmailVerificationPort
) {

  @PostMapping
  fun verifyEmail(
    @Valid
    @RequestBody request: EmailVerificationRequest
  ): ResponseEntity<Boolean> {

    val command = EmailVerificationCommand(
      email = request.email,
      code = request.code
    )

    val isVerify = emailVerificationPort.verifyEmail(command)

    return ResponseEntity.ok().body(isVerify)
  }
}