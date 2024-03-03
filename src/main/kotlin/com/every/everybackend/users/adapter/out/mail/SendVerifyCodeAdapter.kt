package com.every.everybackend.users.adapter.out.mail

import com.every.everybackend.users.port.out.mail.SendVerifyCodePort
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class SendVerifyCodeAdapter(
  private val javaMailSender: JavaMailSender
): SendVerifyCodePort {

  @Async
  override fun sendVerifyCode(email: String, code: String) {
    SimpleMailMessage().apply {
      setTo(email)
      subject = "Every 인증 코드입니다."
      text = "인증 코드: $code"
    }.let {
      javaMailSender.send(it)
    }
  }
}