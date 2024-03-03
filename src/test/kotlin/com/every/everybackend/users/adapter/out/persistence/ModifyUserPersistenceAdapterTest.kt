package com.every.everybackend.users.adapter.out.persistence

import com.every.everybackend.base.MockTest
import com.every.everybackend.common.exception.ApiException
import com.every.everybackend.common.exception.errorcode.UserErrorCode
import com.every.everybackend.users.adapter.out.persistence.entity.UserEntity
import com.every.everybackend.users.adapter.out.persistence.repository.UserRepository
import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import com.every.everybackend.users.domain.enums.UserStatus
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ModifyUserPersistenceAdapterTest: MockTest() {

  @MockK
  private lateinit var userRepository: UserRepository

  @InjectMockKs
  private lateinit var modifyUserPersistenceAdapter: ModifyUserPersistenceAdapter

  @DisplayName("사용자 이메일과 사용자 상태를 입력 받으면 사용자 상태를 수정한다.")
  @Test
  fun `modifyUserStatus success`() {
    // given
    val email = "test@test.com"
    val status = UserStatus.UNVERIFIED

    val userEntity = UserEntity(
      email = email,
      password = "test1234",
      name = "test",
      provider = UserProvider.Email,
      status = status,
      verifyCode = "123456",
      role = UserRole.USER,
    )

    every { userRepository.findByEmail(email) } returns userEntity
    every { userRepository.save(userEntity) } returns userEntity

    // when
    val user = modifyUserPersistenceAdapter.modifyUserStatus(email, UserStatus.ACTIVE)

    // then
    assertEquals(user.status, UserStatus.ACTIVE)
  }

  @DisplayName("사용자 이메일이 없으면 사용자를 찾을 수 없다는 예외를 발생시킨다.")
  @Test
  fun `modifyUserStatus user not found`() {
    // given
    val email = "test@test.com"

    every { userRepository.findByEmail(email) } returns null

    // when
    val exception = assertThrows<ApiException> {
      modifyUserPersistenceAdapter.modifyUserStatus(email, UserStatus.ACTIVE)
    }

    // then
    assertEquals(exception.errorResponse.message, UserErrorCode.USER_NOT_FOUND.getMessage())
  }
}