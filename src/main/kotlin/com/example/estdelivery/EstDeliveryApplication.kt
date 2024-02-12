package com.example.estdelivery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class EstDeliveryApplication

fun main(args: Array<String>) {
    runApplication<EstDeliveryApplication>(*args)
}
