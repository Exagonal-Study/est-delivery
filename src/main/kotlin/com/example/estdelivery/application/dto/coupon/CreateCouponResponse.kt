package com.example.estdelivery.application.dto.coupon

import com.example.estdelivery.common.types.coupon.CouponType
import java.time.LocalDate

data class CreateCouponResponse(
    val name: String,
    val type: CouponType,
    val discountValue: Double,
    val expiryDate: LocalDate
)
