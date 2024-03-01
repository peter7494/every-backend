package com.every.everybackend.users.adapter.out.persistence.repository

import com.every.everybackend.users.adapter.out.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {

  fun findByEmail(email: String): UserEntity?

}