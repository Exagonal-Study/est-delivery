package com.example.estdelivery.domain.coupon

sealed class Coupon(
    open val name: String,
    open val description: String,
    private val couponType: CouponType,
    private val id: Long? = null
) {
    class RateDiscountCoupon(
        val discountRate: Int,
        override val name: String,
        override val description: String,
        couponType: CouponType,
        id: Long? = null,
    ) : Coupon(name, description, couponType, id)

    class FixDiscountCoupon(
        val discountAmount: Int,
        override val name: String,
        override val description: String,
        couponType: CouponType,
        id: Long? = null,
    ) : Coupon(name, description, couponType, id)

    fun isPublished(): Boolean {
        return couponType == CouponType.IS_PUBLISHED
    }

    fun isHandOut(): Boolean {
        return couponType == CouponType.IS_HAND_OUT
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coupon

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}