package com.every.everybackend.users.adapter.`in`.web

import com.every.everybackend.users.adapter.`in`.web.dto.SignupRequest
import com.every.everybackend.users.adapter.`in`.web.dto.UserResponse
import com.every.everybackend.users.port.`in`.web.SignupPort
import com.every.everybackend.users.service.command.SignupCommand
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class SignupController(
  private val signupPort: SignupPort
) {

  @PostMapping
  fun signup(
    @Valid
    @RequestBody request: SignupRequest
  ): ResponseEntity<UserResponse> {

    val signupCommand =
      SignupCommand(email = request.email, password = request.password, name = request.name, image = request.image)

    val user = signupPort.signup(signupCommand)

    return ResponseEntity.ok(UserResponse(
      id = user.id ?: 0L,
      email = user.email,
      name = user.name,
      image = user.image ?: "",
      status = user.status.value
    ))
  }
}