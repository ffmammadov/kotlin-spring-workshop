package io.github.ffmammadov.auth.security

import io.github.ffmammadov.auth.security.filter.JwtSecurityFilter
import io.github.ffmammadov.auth.repository.AuthUserRepository
import io.github.ffmammadov.auth.repository.entity.AuthUserEntity
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Service


@Service
class AuthUserDetailsService(private val repository: AuthUserRepository) : UserDetailsService {

    companion object {
        private val logger = LoggerFactory.getLogger(AuthUserDetailsService::class.java)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.debug("Checking $username credentials")
        val user = repository.findByUsername(username!!)
        return user?.let { AuthUserDetails(it) }!!
    }
}

class AuthUserDetails(private val user: AuthUserEntity) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("USER"))
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return user.active
    }

    override fun isAccountNonLocked(): Boolean {
        return user.active
    }

    override fun isCredentialsNonExpired(): Boolean {
        return user.active
    }

    override fun isEnabled(): Boolean {
        return user.active
    }
}

@Configuration
class SecurityConfiguration(
    private val userDetailsService: AuthUserDetailsService,
    private val jwtFilter: JwtSecurityFilter
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests {
            it.antMatchers("/**/register", "/**/login").permitAll()
                .anyRequest().authenticated()
        }.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().exceptionHandling()
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
