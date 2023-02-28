package io.github.ffmammadov.itemsservice.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class AuthClient(
    private val restTemplate: RestTemplate,
    @Value("\${client.auth.base-url}")private val baseUrl: String
) {
    fun verifyToken(authzHeader: String) {
        val headers = HttpHeaders()
        headers.set("Authorization", authzHeader)
        val requestEntity = HttpEntity<Void>(headers)
        restTemplate.exchange(
            "$baseUrl/api/v1/auth/verify",
            HttpMethod.GET,
            requestEntity,
            Void::class.java
        )
    }
}
