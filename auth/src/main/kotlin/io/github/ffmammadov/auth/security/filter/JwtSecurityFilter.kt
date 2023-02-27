package io.github.ffmammadov.auth.security.filter

import com.auth0.jwt.exceptions.JWTVerificationException
import io.github.ffmammadov.auth.security.AuthUserDetailsService
import io.github.ffmammadov.auth.service.JwtService
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtSecurityFilter(
    private val jwtService: JwtService, private val userDetailsService: AuthUserDetailsService
) : OncePerRequestFilter() {

    companion object {
        private val lgr = LoggerFactory.getLogger(JwtSecurityFilter::class.java)
    }

    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        val authzHeaderValue = request.getHeader("Authorization")

        if (authzHeaderValue != null && authzHeaderValue.startsWith("Bearer ")) {
            val token = authzHeaderValue.substringAfter("Bearer ")
            lgr.debug("Token is: $token")
            try {
                val username = jwtService.verifyToken(token)
                lgr.debug("Username from token is: $username")
                val userDetails = userDetailsService.loadUserByUsername(username)
                val passwordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                passwordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = passwordAuthenticationToken
            } catch (e: JWTVerificationException) {
                lgr.error("Token verification failed", e)
            }
        }
        filterChain.doFilter(request, response)
    }
}
