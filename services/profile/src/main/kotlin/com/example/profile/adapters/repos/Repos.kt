
package com.example.profile.adapters.repos

import com.example.profile.domain.*
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.Optional

@Repository
interface ConsumerRepo : JpaRepository<Consumer, String>

@Repository
interface ProfileRepo : JpaRepository<Profile, String>

@Repository
interface TraitRepo : JpaRepository<Trait, Long> {
    fun findByConsumerId(consumerId: String): List<Trait>
}

@Repository
interface EventLogRepo : JpaRepository<EventLog, Long> {
    fun findByConsumerId(consumerId: String): List<EventLog>
    fun findByKey(key: String): Optional<EventLog>
}
