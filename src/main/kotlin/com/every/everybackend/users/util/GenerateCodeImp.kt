package com.every.everybackend.users.util

import org.springframework.stereotype.Component

@Component
class GenerateCodeImp: GenerateCode {
    override fun generateCode(length: Int): String {
      val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
      return (1..length)
        .map { kotlin.random.Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
    }
}