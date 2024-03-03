package com.every.everybackend.users.service

import com.every.everybackend.base.MockTest
import com.every.everybackend.users.adapter.out.persistence.SignupPersistenceAdapter
import com.every.everybackend.users.domain.User
import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import com.every.everybackend.users.domain.enums.UserStatus
import com.every.everybackend.users.port.out.mail.SendVerifyCodePort
import com.every.everybackend.users.service.command.SignupCommand
import com.every.everybackend.users.util.GenerateCode
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SignupServiceTest: MockTest() {

  @MockK
  private lateinit var signupPersistenceAdapter: SignupPersistenceAdapter

  @MockK
  private lateinit var sendVerifyCodePort: SendVerifyCodePort

  @MockK
  private lateinit var generateCode: GenerateCode

  @InjectMockKs
  private lateinit var signupService: SignupService

  @DisplayName("사용자 정보를 입력 받으면 사용자를 생성한다.")
  @Test
  fun `signup success`() {

    // given
    val email = "test@test.com"
    val password = "test1234"
    val name = "test"

    val signupCommand = SignupCommand(
      email = email,
      password = password,
      name = name,
      image = null
    )

    val user = User(
      email = email,
      password = password,
      name = name,
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED,
      role = UserRole.USER,
      verifyCode = "123456"
    )

    every { generateCode.generateCode(6) } returns "123456"
    every { signupPersistenceAdapter.createUser(user) } returns user
    every { sendVerifyCodePort.sendVerifyCode(user.email, any()) } returns Unit

    // when
    val createUser = signupService.signup(signupCommand)

    // then
    verify { sendVerifyCodePort.sendVerifyCode(user.email, any()) }
    assertEquals(email, createUser.email)
    assertEquals(password, createUser.password)
    assertEquals(name, createUser.name)
  }
}