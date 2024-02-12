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
        validateDiscountValue()
        validateExpiryDate()
        validateRateCouponDiscountValue()
    }

    companion object {
        private const val MAX_DISCOUNT_RATE = 1.0
    }

    private fun validateDiscountValue() {
        if (discountValue < 0) {
            throw IllegalArgumentException("Discount value cannot be negative")
        }
    }
    private fun validateExpiryDate() {
        val tomorrow = LocalDate.now().plusDays(1)
        if (tomorrow.isAfter(expiryDate)) {
            throw IllegalArgumentException("Expiry date cannot be in the past")
        }
    }

    private fun validateRateCouponDiscountValue() {
        if (type == CouponType.RATE && discountValue > MAX_DISCOUNT_RATE) {
            throw IllegalArgumentException("Rate coupon discount value cannot be greater than $MAX_DISCOUNT_RATE")
        }
    }

    fun isExpired() {
        val tomorrow = LocalDate.now().plusDays(1)
        if (tomorrow.isAfter(expiryDate)) {
            throw IllegalArgumentException("Coupon is expired")
        }
    }
}
