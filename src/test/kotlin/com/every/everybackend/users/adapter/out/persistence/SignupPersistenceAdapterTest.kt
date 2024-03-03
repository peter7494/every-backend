package com.every.everybackend.users.adapter.out.persistence

import com.every.everybackend.base.MockTest
import com.every.everybackend.common.exception.ApiException
import com.every.everybackend.common.exception.errorcode.UserErrorCode
import com.every.everybackend.users.adapter.out.persistence.entity.UserEntity
import com.every.everybackend.users.adapter.out.persistence.repository.UserRepository
import com.every.everybackend.users.domain.User
import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import com.every.everybackend.users.domain.enums.UserStatus
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SignupPersistenceAdapterTest: MockTest() {

  @MockK
  private lateinit var userRepository: UserRepository

  @InjectMockKs
  private lateinit var signupPersistenceAdapter: SignupPersistenceAdapter

  @DisplayName("사용자 정보를 입력 받으면 사용자를 생성한다.")
  @Test
  fun `signup success`() {
    // given
    val email = "test@test.com"
    val password = "test1234"
    val name = "test"

    val user = User(
      email = email,
      password = password,
      name = name,
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED,
      role = UserRole.USER
    )

    every { userRepository.findByEmail(email) } returns null
    every { userRepository.save(UserEntity(
      email = user.email,
      password = user.password,
      name = user.name,
      role = user.role,
      provider = user.provider,
      status = user.status
    )) } returns UserEntity(
      id = 1,
      email = email,
      password = password,
      name = name,
      role = UserRole.USER,
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED
    )
    // when

    val createUser = signupPersistenceAdapter.createUser(user)

    // then

    assertNotNull(createUser.id)
    assertEquals(email, createUser.email)
    assertEquals(name, createUser.name)
  }

  @DisplayName("이미 가입된 사용자가 있으면 에러를 반환한다.")
  @Test
  fun `signup fail`() {
    // given
    val email = "test@test.com"
    val password = "test1234"
    val name = "test"

    val user = User(
      email = email,
      password = password,
      name = name,
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED,
      role = UserRole.USER
    )

    every { userRepository.findByEmail(email) } returns UserEntity(
      id = 1,
      email = email,
      password = password,
      name = name,
      role = UserRole.USER,
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED
    )

    // when
    val exception = assertThrows<ApiException> {
      signupPersistenceAdapter.createUser(user)
    }

    // then
    assertEquals(UserErrorCode.USER_ALREADY_EXISTS.getMessage(), exception.errorResponse.message)
    assertEquals(UserErrorCode.USER_ALREADY_EXISTS.getCode(), exception.errorResponse.code)
    assertEquals(UserErrorCode.USER_ALREADY_EXISTS.getStatus(), exception.errorResponse.status)
  }

}
