package io.github.ffmammadov.auth.repository

import io.github.ffmammadov.auth.repository.entity.AuthUserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthUserRepository : CrudRepository<AuthUserEntity, Long> {
    fun findByUsername(username: String): AuthUserEntity?
}
