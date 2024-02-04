package com.example.estdelivery.domain.coupon

sealed class Coupon(
    open val name: String,
    open val description: String,
    private val couponType: CouponType
) {
    class RateDiscountCoupon(
        val discountRate: Int,
        override val name: String,
        override val description: String,
        couponType: CouponType
    ) : Coupon(name, description, couponType)
    class FixDiscountCoupon(
        val discountAmount: Int,
        override val name: String,
        override val description: String,
        couponType: CouponType
    ) : Coupon(name, description, couponType)

    fun isPublished(): Boolean {
        return couponType == CouponType.IS_PUBLISHED
    }

    fun isHandOut(): Boolean {
        return couponType == CouponType.IS_HAND_OUT
    }
}