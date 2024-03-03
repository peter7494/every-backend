package com.every.everybackend.users.port.out.mail

interface SendVerifyCodePort {

  fun sendVerifyCode(email: String, code: String)
}