package io.github.ffmammadov.itemsservice.security

import io.github.ffmammadov.itemsservice.client.AuthClient
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter(private val authClient: AuthClient) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authzHeader = request.getHeader("Authorization")
            authClient.verifyToken(authzHeader)
        } catch (e: Exception) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials")
            return
        }
        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath.startsWith("/api/v1/public/item")
    }
}
