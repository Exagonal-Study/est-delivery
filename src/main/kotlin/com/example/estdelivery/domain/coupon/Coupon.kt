package com.example.estdelivery.domain.coupon

import com.example.estdelivery.common.types.coupon.CouponType
import java.time.LocalDate

class Coupon(
    val name: String,
    val type: CouponType,
    val discountValue: Double,
    val expiryDate: LocalDate
) {
    init {
        if (discountValue < 0) {
            throw Exception("Discount value cannot be negative")
        }

        if (LocalDate.now().isAfter(expiryDate)) {
            throw Exception("Expiry date cannot be in the past")
        }

        if (type == CouponType.RATE && discountValue > 1) {
            throw Exception("Rate coupon discount value cannot be greater than 1")
        }
    }
}
