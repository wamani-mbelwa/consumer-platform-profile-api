
package com.example.profile.config

import com.example.profile.usecase.ProfileService
import jakarta.inject.Singleton

@Singleton
class SeedTask(private val svc: ProfileService) {
    fun run() {
        listOf("demo-user-1","demo-user-2").forEach { id ->
            svc.addTrait(id, "segment", if (id.endsWith("1")) "builder" else "browser")
        }
    }
}
