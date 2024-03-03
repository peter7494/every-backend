package com.every.everybackend.users.adapter.out.mail

import com.every.everybackend.users.port.out.mail.SendVerifyCodePort
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("test")
class MockSendVerifyCodeAdapter: SendVerifyCodePort {
  override fun sendVerifyCode(email: String, code: String) {
    println("send verify code to $email: $code")
  }
}