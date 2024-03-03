package com.every.everybackend.users.adapter.`in`.web

import com.every.everybackend.base.MvcTest
import com.every.everybackend.users.adapter.`in`.web.dto.EmailVerificationRequest
import com.every.everybackend.users.adapter.out.persistence.entity.UserEntity
import com.every.everybackend.users.adapter.out.persistence.repository.UserRepository
import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import com.every.everybackend.users.domain.enums.UserStatus
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class EmailVerificationControllerTest : MvcTest() {

  @Autowired
  private lateinit var userRepository: UserRepository

  @BeforeEach
  fun setUp() {
    val user = UserEntity(
      email = "test@test.com",
      password = "test1234",
      name = "test",
      provider = UserProvider.Email,
      status = UserStatus.UNVERIFIED,
      verifyCode = "123456",
      role = UserRole.USER,
    )

    userRepository.save(user)
  }

  @DisplayName("사용자 이메일과 인증 번호를 입력 받으면 사용자 이메일 인증을 한다.")
  @Test
  fun `verifyEmail success`() {
    // given
    val email = "test@test.com"
    val code = "123456"

    val request = EmailVerificationRequest(
      email = email,
      code = code
    )
    // when && then
    mockMvc.perform(
      post("/api/email-verification")
        .contentType(MediaType.APPLICATION_JSON)
        .content(convertObjectToJsonBytes(request))
    )
      .andExpect(status().isOk)
      .andDo(document("email-verification/success"))
  }

  @DisplayName("사용자 이메일이 없으면 사용자를 찾을 수 없다는 예외를 발생시킨다.")
  @Test
  fun `verifyEmail user not found`() {
    // given
    val email = "test2@test.com"
    val code = "123456"

    val request = EmailVerificationRequest(
      email = email,
      code = code
    )

    // when && then
    mockMvc.perform(
      post("/api/email-verification")
        .contentType(MediaType.APPLICATION_JSON)
        .content(convertObjectToJsonBytes(request))
    )
      .andExpect(status().isNotFound)
      .andDo(document("email-verification/user-not-found"))

  }

@DisplayName("가입된 이메일과 올바르지 않은 인증 코드를 입력하면 false 반환한다.")
@Test
  fun `verifyEmail fail`() {
  // given
  val email = "test@test.com"
  val code = "qweasd"

  val request = EmailVerificationRequest(
    email = email,
    code = code
  )

  // when && then
  mockMvc.perform(
    post("/api/email-verification")
      .contentType(MediaType.APPLICATION_JSON)
      .content(convertObjectToJsonBytes(request))
  )
    .andExpect(status().isOk)
    .andDo(document("email-verification/fail"))
  }

}