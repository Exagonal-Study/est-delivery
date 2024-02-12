package com.example.estdelivery.domain.coupon

import com.example.estdelivery.common.types.coupon.CouponType
import java.time.LocalDate

class Coupon(
    val id: Long? = null,
    val name: String,
    val type: CouponType,
    val discountValue: Double,
    val expiryDate: LocalDate
) {
    init {
        if (discountValue < 0) {
            throw IllegalArgumentException("Discount value cannot be negative")
        }

        if (LocalDate.now().isAfter(expiryDate)) {
            throw IllegalArgumentException("Expiry date cannot be in the past")
        }

        if (type == CouponType.RATE && discountValue > 1) {
            throw IllegalArgumentException("Rate coupon discount value cannot be greater than 1")
        }
    }

    fun validateExpiryDate() {
        if (LocalDate.now().isAfter(expiryDate)) {
            throw IllegalArgumentException("Coupon is expired")
        }
    }
}
