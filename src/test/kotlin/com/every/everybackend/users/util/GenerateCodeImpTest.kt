package com.every.everybackend.users.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GenerateCodeImpTest {

  @Test
  fun `generateCode should return 6 length code`() {
    val generateCode = GenerateCodeImp().generateCode(6)

    assertEquals(6, generateCode.length)
  }
}