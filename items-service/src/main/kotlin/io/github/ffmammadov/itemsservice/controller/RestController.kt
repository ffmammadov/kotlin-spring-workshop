package io.github.ffmammadov.itemsservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/item")
class RestController {

    @GetMapping("/health")
    fun health(): Map<String, String> {
        return mapOf("response" to "I'm Healthy!")
    }
}
