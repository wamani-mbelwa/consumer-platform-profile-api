
package com.example.profile.adapters.http

import com.example.profile.usecase.ProfileService
import io.micronaut.http.annotation.*
import io.micronaut.http.HttpStatus

@Controller("/v1")
class ProfileController(private val svc: ProfileService) {

    @Get("/profile/{id}")
    fun profile(@PathVariable id: String) = svc.getProfile(id)

    @Get("/recommendations/{id}")
    fun recs(@PathVariable id: String) = svc.getRecommendations(id)

    data class TraitReq(val name: String, val value: String)

    @Post("/traits/{id}")
    @Status(HttpStatus.ACCEPTED)
    fun addTrait(@PathVariable id: String, @Body req: TraitReq) = svc.addTrait(id, req.name, req.value)

    @Get("/prometheus")
    fun prom() = "profile_freshness_seconds 1.0
" // placeholder metric endpoint (Micrometer handles real metrics)
}
