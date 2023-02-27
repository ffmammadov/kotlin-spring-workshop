package io.github.ffmammadov.auth.service

import io.github.ffmammadov.auth.model.CredentialsDto
import io.github.ffmammadov.auth.model.RegisterResponseDto
import io.github.ffmammadov.auth.repository.AuthUserRepository
import io.github.ffmammadov.auth.repository.entity.AuthUserEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val passwordEncoder: PasswordEncoder,
    private val repository: AuthUserRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {

    fun register(dto: CredentialsDto): RegisterResponseDto {
        val entity = AuthUserEntity(username = dto.username, password = passwordEncoder.encode(dto.password))
        val saved = repository.save(entity)
        return RegisterResponseDto(saved.id!!, saved.username, saved.active, saved.createdAt!!, saved.updatedAt!!)
    }

    fun login(dto: CredentialsDto): Map<String, String> {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(dto.username, dto.password))
        return mapOf("token" to jwtService.generateToken(dto.username))
    }
}
