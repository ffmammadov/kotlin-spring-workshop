package io.github.ffmammadov.itemsservice.repository.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "items")
data class ItemEntity(
    var name: String,
    var price: Int,
    var available: Boolean,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int?= null,
    @CreationTimestamp val createdAt: LocalDateTime? = null,
    @UpdateTimestamp val updatedAt: LocalDateTime? = null,
    var unavailabilityReason: String? = null
)
