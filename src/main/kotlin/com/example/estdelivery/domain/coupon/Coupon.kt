package com.example.estdelivery.domain.coupon

open class Coupon(
    open val name: String,
    open val description: String,
    private val couponType: CouponType
) {
    fun isPublished(): Boolean {
        return couponType == CouponType.IS_PUBLISHED
    }

    fun isHandOut(): Boolean {
        return couponType == CouponType.IS_HAND_OUT
    }
}