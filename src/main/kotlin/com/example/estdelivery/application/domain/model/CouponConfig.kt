package com.example.estdelivery.application.domain.model

data class CouponConfig(
    val id: Long,
    val type: CouponType,
    val json: String
)