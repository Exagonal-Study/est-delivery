package com.example.estdelivery.domain.coupon

class RateDiscountCoupon(
    val discountRate: Int,
    override val name: String,
    override val description: String,
    private val couponType: CouponType
): Coupon(name, description, couponType) {


}