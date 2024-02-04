package com.example.estdelivery.domain.coupon

sealed class Coupon(
    private val id: Long,
    open val name: String,
    open val description: String,
    private val couponType: CouponType
) {
    class RateDiscountCoupon(
        id: Long,
        val discountRate: Int,
        override val name: String,
        override val description: String,
        couponType: CouponType
    ) : Coupon(id, name, description, couponType)

    class FixDiscountCoupon(
        id: Long,
        val discountAmount: Int,
        override val name: String,
        override val description: String,
        couponType: CouponType
    ) : Coupon(id, name, description, couponType)

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