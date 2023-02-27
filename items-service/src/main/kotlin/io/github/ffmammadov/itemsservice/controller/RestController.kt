package io.github.ffmammadov.itemsservice.controller

import io.github.ffmammadov.itemsservice.ItemService
import io.github.ffmammadov.itemsservice.model.ItemDto
import io.github.ffmammadov.itemsservice.repository.ItemsRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/item")
class RestController(private val service: ItemService) {

    @GetMapping("/health")
    fun health(): Map<String, String> {
        return mapOf("response" to "I'm Healthy!")
    }

    @PostMapping("", "/")
    fun saveItem(@RequestBody dto: ItemDto): ItemDto {
        return service.saveItem(dto)
    }

    @GetMapping("/{id}")
    fun getItemById(@PathVariable id: Int): ItemDto {
        return service.getItemById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteItemById(@PathVariable id: Int) {
        service.deleteItemById(id)
    }

    @PutMapping("/{id}")
    fun updateItemById(@PathVariable id: Int, @RequestBody dto: ItemDto): ItemDto {
        return service.updateItemById(id, dto);
    }
}
