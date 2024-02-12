package com.example.estdelivery.application.port.`in`

data class IssueCouponCommand(
    val userId: Long,
    val couponConfigId: Long
)
