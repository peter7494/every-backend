package com.every.everybackend.users.adapter.`in`.web

import com.every.everybackend.base.MvcTest
import com.every.everybackend.users.adapter.`in`.web.dto.SignupRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class SignupControllerTest: MvcTest() {

  @DisplayName("정상적인 회원가입")
  @Test
  fun `signup success`() {
    // given
    val email = "test@test.com"
    val password = "test1234"
    val name = "test"

    val signupRequest = SignupRequest(
      email = email,
      password = password,
      name = name,
      image = ""
    )

    // when

  mockMvc.perform(post("/api/users")
    .contentType(MediaType.APPLICATION_JSON)
    .content(convertObjectToJsonBytes(signupRequest))
  )
    .andExpect(status().isOk)
    .andExpect(jsonPath("email").value(email))
    .andExpect(jsonPath("name").value(name))
    .andDo(document("signup/success"))
  }

  @DisplayName("이메일 중복 회원가입")
  @Test
  fun `signup fail because of duplicated email`() {
    // given
    val email = "test@test.com"
    val password = "test1234"
    val name = "test"

    val signupRequest = SignupRequest(
      email = email,
      password = password,
      name = name,
      image = ""
    )

    // when
    mockMvc.perform(
      post("/api/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(convertObjectToJsonBytes(signupRequest))
    )

    // then
    val result = mockMvc.perform(
      post("/api/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(convertObjectToJsonBytes(signupRequest))
    )
      .andExpect(status().isBadRequest)
      .andDo(document("signup/duplicated-email"))

  }

}