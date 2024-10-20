
package com.example.profile.domain

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name="consumers")
data class Consumer(
    @Id val id: String,
    val email: String
)

@Entity
@Table(name="profiles")
data class Profile(
    @Id val consumerId: String,
    val displayName: String? = null,
    val updatedAt: Instant = Instant.now()
)

@Entity
@Table(name="traits")
data class Trait(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val consumerId: String,
    val name: String,
    val value: String,
    val createdAt: Instant = Instant.now()
)

@Entity
@Table(name="event_log")
data class EventLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val key: String,
    val consumerId: String,
    val type: String,
    val payload: String,
    val createdAt: Instant = Instant.now()
)
