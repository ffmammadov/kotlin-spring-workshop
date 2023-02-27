package io.github.ffmammadov.auth.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(
    @Value("\${security.jwt.secret}")
    private val secret: String
) {
    private val algorithm = Algorithm.HMAC512(secret)

    fun generateToken(username: String): String {
        return JWT.create()
            .withSubject(username)
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
            .sign(algorithm)
    }

    fun verifyToken(token: String): String {
        val decoded = JWT.require(algorithm).build().verify(token)
        return decoded.subject
    }
}
