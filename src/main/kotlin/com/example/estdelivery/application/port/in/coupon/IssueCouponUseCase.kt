package com.example.estdelivery.application.port.`in`.coupon

interface IssueCouponUseCase {
    fun issueCoupon(memberId: Long, couponId: Long)
}