package com.every.everybackend.users.adapter.out.persistence

import com.every.everybackend.base.MockTest
import com.every.everybackend.users.adapter.out.persistence.entity.UserEntity
import com.every.everybackend.users.adapter.out.persistence.repository.UserRepository
import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import com.every.everybackend.users.domain.enums.UserStatus
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class FindUserPersistenceAdapterTest: MockTest() {

  @MockK
  private lateinit var userRepository: UserRepository

  @InjectMockKs
  private lateinit var findUserPersistenceAdapter: FindUserPersistenceAdapter

  @DisplayName("이메일로 사용자를 찾는다.")
  @Test
  fun findByEmail() {
    // given
    val email = "test@test.com"
    val userEntity = UserEntity(
      email = email,
      password = "password",
      name = "name",
      image = "image",
      role = UserRole.USER,
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED,
      providerId = ""
    )

    every { userRepository.findByEmail(email) } returns userEntity


    // when
    val result = findUserPersistenceAdapter.findByEmail(email)

    // then
    assertEquals(result?.email, email)
  }

  @DisplayName("이메일로 사용자를 찾지 못하면 null을 반환한다.")
  @Test
  fun findByEmail_null() {
    // given
    val email = "test@test.com"
    every { userRepository.findByEmail(email) } returns null

    // when
    val result = findUserPersistenceAdapter.findByEmail(email)

    // then
    assertNull(result)
    }
}