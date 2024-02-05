package com.example.estdelivery.common.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object DateUtils {
    fun LocalDate.atEndOfDay(): LocalDateTime = this.atTime(LocalTime.MAX)
}