
package com.example.profile.usecase

import com.example.profile.adapters.messaging.ConsumerEvent
import com.example.profile.adapters.messaging.EventsProducer
import com.example.profile.adapters.repos.*
import com.example.profile.domain.Profile
import com.example.profile.domain.Trait
import jakarta.inject.Singleton
import java.time.Instant

@Singleton
class ProfileService(
    private val consumerRepo: ConsumerRepo,
    private val profileRepo: ProfileRepo,
    private val traitRepo: TraitRepo,
    private val eventsProducer: EventsProducer
) {
    fun getProfile(id: String): Map<String, Any?> {
        val profile = profileRepo.findById(id).orElse(Profile(id, null, Instant.now()))
        val traits = traitRepo.findByConsumerId(id).associate { it.name to it.value }
        return mapOf(
            "consumerId" to id,
            "displayName" to profile.displayName,
            "traits" to traits,
            "updatedAt" to profile.updatedAt.toString()
        )
    }

    fun getRecommendations(id: String): Map<String, Any?> {
        // demo: derive from traits
        val traits = traitRepo.findByConsumerId(id).associate { it.name to it.value }
        val recs = if (traits.containsKey("segment") && traits["segment"]=="builder") {
            listOf("HELOC pre-qualification", "Document checklist")
        } else listOf("Complete profile", "Start application")
        return mapOf("consumerId" to id, "recommendations" to recs)
    }

    fun addTrait(id: String, name: String, value: String): Map<String, Any?> {
        traitRepo.save(Trait(consumerId=id, name=name, value=value))
        // emit event to Kafka for downstream materializers
        eventsProducer.publish(
            ConsumerEvent(
                key = "trait:$id:$name",
                consumerId = id,
                type = "trait_added",
                payload = mapOf("name" to name, "value" to value)
            )
        )
        return mapOf("status" to "ok")
    }
}
