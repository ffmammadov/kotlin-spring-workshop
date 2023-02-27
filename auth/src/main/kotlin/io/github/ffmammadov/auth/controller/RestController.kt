package io.github.ffmammadov.auth.controller

import io.github.ffmammadov.auth.model.CredentialsDto
import io.github.ffmammadov.auth.model.RegisterResponseDto
import io.github.ffmammadov.auth.service.AuthService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class RestController(private val authService: AuthService) {

    @PostMapping("/register")
    fun register(@RequestBody dto: CredentialsDto): RegisterResponseDto {
        return authService.register(dto)
    }

    @PostMapping("/login")
    fun login(@RequestBody credentials: CredentialsDto): Map<String, String> {
        return authService.login(credentials)
    }

    @GetMapping("/verify")
    fun verify() {}
}
