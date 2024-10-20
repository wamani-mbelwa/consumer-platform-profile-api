
package com.example.profile.adapters.messaging

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ConsumerEvent(
    val key: String,
    val consumerId: String,
    val type: String,
    val payload: Map<String, Any?> = emptyMap()
)
