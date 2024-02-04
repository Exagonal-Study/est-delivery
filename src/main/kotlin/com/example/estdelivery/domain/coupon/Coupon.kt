package com.example.estdelivery.domain.coupon

data class Coupon(
    val name: String,
    val description: String,
    private val couponType: CouponType
) {
    fun isPublished(): Boolean {
        return couponType == CouponType.IS_PUBLISHED
    }

    fun isHandOut(): Boolean {
        return couponType == CouponType.IS_HAND_OUT
    }
}