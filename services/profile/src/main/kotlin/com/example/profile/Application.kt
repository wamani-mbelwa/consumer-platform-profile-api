
package com.example.profile

import io.micronaut.runtime.Micronaut

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .packages("com.example.profile")
            .start()
    }
}
