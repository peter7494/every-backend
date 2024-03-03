package com.every.everybackend.users.adapter.out.persistence.entity

import com.every.everybackend.users.domain.enums.UserProvider
import com.every.everybackend.users.domain.enums.UserRole
import com.every.everybackend.users.domain.enums.UserStatus
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = "users")
@EntityListeners(AuditingEntityListener::class)
data class UserEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(nullable = false, unique = true, length = 50)
  val email: String,

  @Column(nullable = false, unique = true, length = 20)
  var name: String,

  @Column(nullable = false, length = 100)
  var password: String,

  var image: String? = null,

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  val role: UserRole,

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  var status: UserStatus,

  @Column(length = 6)
  var verifyCode: String? = null,

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  val provider: UserProvider,

  @Column(length = 100)
  val providerId: String? = null,

  @CreatedDate
  val createdAt: LocalDateTime? = null,

  @LastModifiedDate
  val updatedAt: LocalDateTime? = null
)
