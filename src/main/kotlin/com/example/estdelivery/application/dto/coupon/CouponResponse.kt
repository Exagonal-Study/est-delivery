package com.example.estdelivery.application.dto.coupon

import com.example.estdelivery.domain.coupon.types.CouponEventType
import com.example.estdelivery.domain.coupon.types.CouponType

data class CouponResponse(
    val couponName: String,
    val couponType: CouponType,
    val couponEventType: CouponEventType,
    val quantity: Long
)
