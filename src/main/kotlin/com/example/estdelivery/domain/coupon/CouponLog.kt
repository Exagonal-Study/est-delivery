package com.example.estdelivery.domain.coupon

import java.time.LocalDateTime

class CouponLog(
    val userId: Long,
    val couponId: String,
    val createdDate: LocalDateTime? = null
)
