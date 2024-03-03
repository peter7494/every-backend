package com.every.everybackend.users.service

import com.every.everybackend.base.MockTest
import com.every.everybackend.common.exception.ApiException
import com.every.everybackend.common.exception.errorcode.UserErrorCode
import com.every.everybackend.users.domain.User
import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import com.every.everybackend.users.domain.enums.UserStatus
import com.every.everybackend.users.port.out.persistence.FindUserPersistencePort
import com.every.everybackend.users.port.out.persistence.ModifyUserPersistencePort
import com.every.everybackend.users.service.command.EmailVerificationCommand
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EmailVerificationServiceTest: MockTest() {

  @MockK
  private lateinit var findUserPersistencePort: FindUserPersistencePort

  @MockK
  private lateinit var modifyUserPersistencePort: ModifyUserPersistencePort

  @InjectMockKs
  private lateinit var emailVerificationService: EmailVerificationService

  @DisplayName("가입된 이메일과 올바른 인증 코드를 입력하면 사용자를 활성화하고 true 반환한다.")
  @Test
  fun `verifyEmail success`() {
    // given
    val email = "test@test.com"
    val code = "123456"

    val user = User(
      email = email,
      password = "test1234",
      name = "test",
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED,
      verifyCode = code,
      role = UserRole.USER,
    )

    val activeUser = User(
      email = email,
      password = "test1234",
      name = "test",
      provider = UserProvider.Email,
      status = UserStatus.ACTIVE,
      verifyCode = code,
      role = UserRole.USER,
    )


    every { findUserPersistencePort.findByEmail(email) } returns user
    every { modifyUserPersistencePort.modifyUserStatus(email, UserStatus.ACTIVE) } returns activeUser

    // when
    val result = emailVerificationService.verifyEmail(EmailVerificationCommand(
      email = email,
      code = code
    ))

    // then
    assertTrue(result)
  }

  @DisplayName("가입된 이메일과 올바르지 않은 인증 코드를 입력하면 false 반환한다.")
  @Test
  fun `verifyEmail fail`() {
    // given
    val email = "test@test.com"
    val code = "123456"

    val user = User(
      email = email,
      password = "test1234",
      name = "test",
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED,
      verifyCode = code,
      role = UserRole.USER,
    )

    every { findUserPersistencePort.findByEmail(email) } returns user

    // when
    val result = emailVerificationService.verifyEmail(
      EmailVerificationCommand(
        email = email,
        code = "654321"
      )
    )

    // then
    assertFalse(result)
  }

  @DisplayName("가입되지 않은 이메일을 입력하면 사용자를 찾을 수 없다는 예외를 발생시킨다.")
  @Test
  fun `verifyEmail user not found`() {
    // given
    val email = "test@test.com"

    every { findUserPersistencePort.findByEmail(email) } returns null

    // when
    val exception = assertThrows<ApiException> {
      emailVerificationService.verifyEmail(
        EmailVerificationCommand(
          email = email,
          code = "123456"
        )
      )
    }

    // then
    assertEquals(exception.errorResponse.message, UserErrorCode.USER_NOT_FOUND.getMessage())
  }
}