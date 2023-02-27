package io.github.ffmammadov.auth.repository.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "auth_users", uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("username"))])
class AuthUserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val username: String,
    val password: String,
    val active: Boolean = true,
    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null
)
