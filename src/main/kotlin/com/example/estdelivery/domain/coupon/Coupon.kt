package com.example.estdelivery.domain.coupon

import com.example.estdelivery.common.types.coupon.CouponType
import java.time.LocalDate
import kotlin.math.max

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

    fun applyDiscount(originalPrice: Double): Double {
        val discountedAmount = when (type) {
            CouponType.RATE -> originalPrice * (1 - discountValue) // 비율 할인 적용
            CouponType.AMOUNT -> originalPrice - discountValue // 금액 할인 적용
        }
        return max(discountedAmount, 0.0) // 할인된 가격이 0보다 작지 않도록 보장
    }
}
