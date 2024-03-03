package com.every.everybackend.users.adapter.out.persistence

import com.every.everybackend.base.MockTest
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
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RegisterUserPersistenceAdapterTest: MockTest() {

  @MockK
  private lateinit var userRepository: UserRepository

  @InjectMockKs
  private lateinit var registerUserPersistenceAdapter: RegisterUserPersistenceAdapter

  @DisplayName("사용자 정보를 입력 받으면 사용자를 생성한다.")
  @Test
  fun registerUser() {
    // given
    val email = "test@test.com"
    val password = "password"
    val name = "name"
    val image = "image"

    val user = User(
      email = email,
      password = password,
      name = name,
      image = image,
      role = UserRole.USER,
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED,
    )

    val userEntity = UserEntity(
      email = user.email,
      password = user.password,
      name = user.name,
      image = user.image,
      role = user.role,
      provider = user.provider,
      status = user.status,
      providerId = user.providerId,
    )

    every { userRepository.save(userEntity) } returns userEntity

    // when
    val result = registerUserPersistenceAdapter.registerUser(user)

    // then
    assertEquals(result.email, email)
    assertEquals(result.password, password)
    assertEquals(result.name, name)
    assertEquals(result.image, image)
    assertEquals(result.role, UserRole.USER)
    assertEquals(result.provider, UserProvider.Email)
    assertEquals(result.status, UserStatus.UNVERIFIED)
  }
}