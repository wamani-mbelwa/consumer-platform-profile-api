
package com.example.profile

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client

@MicronautTest
class SmokeTest {

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun health() {
        val res = client.toBlocking().retrieve("/v1/recommendations/demo-user-1")
        assertTrue(res.contains("recommendations"))
    }
}
