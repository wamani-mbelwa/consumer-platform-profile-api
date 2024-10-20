
package com.example.profile.adapters.messaging

import com.example.profile.adapters.repos.EventLogRepo
import com.example.profile.domain.EventLog
import io.micronaut.configuration.kafka.annotation.*
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@KafkaClient
interface EventsProducer {
    @Topic("consumer.events")
    fun publish(event: ConsumerEvent)
}

@Singleton
@Requires(property="kafka.bootstrap.servers")
class EventsConsumer(private val eventLogRepo: EventLogRepo) {

    private val mapper = jacksonObjectMapper()

    @Topic("consumer.events")
    fun onEvent(event: ConsumerEvent) {
        // naive idempotency using key uniqueness
        if (eventLogRepo.findByKey(event.key).isEmpty) {
            eventLogRepo.save(EventLog(
                key = event.key,
                consumerId = event.consumerId,
                type = event.type,
                payload = mapper.writeValueAsString(event.payload)
            ))
        }
    }
}
