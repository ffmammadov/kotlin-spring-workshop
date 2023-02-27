package io.github.ffmammadov.itemsservice

import io.github.ffmammadov.itemsservice.model.ItemDto
import io.github.ffmammadov.itemsservice.repository.ItemsRepository
import io.github.ffmammadov.itemsservice.repository.entity.ItemEntity
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ItemService(private val repository: ItemsRepository) {

    fun saveItem(itemDto: ItemDto): ItemDto {
        val entity = itemDto.run { ItemEntity(name, price, available) }
        val saved = repository.save(entity)
        return saved.run { ItemDto(name, price, id, available, createdAt, updatedAt, unavailabilityReason) }
    }

    fun getItemById(id: Int): ItemDto {
        val entity = repository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        return entity.run { ItemDto(name, price, id, available, createdAt, updatedAt, unavailabilityReason) }
    }

    fun deleteItemById(id: Int) {
        repository.deleteById(id)
    }

    fun updateItemById(id: Int, dto: ItemDto): ItemDto {
        val entity = repository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        entity.apply {
            name = dto.name
            price = dto.price
            available = dto.available
            unavailabilityReason = dto.unavailabilityReason
        }
        val saved = repository.save(entity)
        return saved.run { ItemDto(name, price, this.id, available, createdAt, updatedAt, unavailabilityReason) }
    }

    fun getItemByName(name: String): ItemDto {
        val entity = repository.findByName(name) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return entity.run { ItemDto(name, price, this.id, available, createdAt, updatedAt, unavailabilityReason) }
    }

    fun getItems(): List<ItemDto> {
        return repository.findAll()
            .map {
                ItemDto(
                    it.name,
                    it.price,
                    it.id,
                    it.available,
                    it.createdAt,
                    it.updatedAt,
                    it.unavailabilityReason
                )
            }
    }

}
