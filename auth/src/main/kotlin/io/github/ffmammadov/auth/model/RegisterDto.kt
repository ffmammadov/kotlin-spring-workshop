package io.github.ffmammadov.auth.model

import java.time.LocalDateTime

data class CredentialsDto(val username: String, val password: String)

data class RegisterResponseDto(
    val id: Long,
    val username: String,
    val active: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
