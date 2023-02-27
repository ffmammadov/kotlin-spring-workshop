package io.github.ffmammadov.itemsservice.model

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

data class ItemDto(
    val name: String,
    val price: Int,
    val id: Int? = null,
    val available: Boolean = true,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val unavailabilityReason: String? = null
)
