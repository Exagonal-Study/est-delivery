package com.example.estdelivery.application.port.out.state

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponType

private const val PUBLISHED = "PUBLISHED"
private const val HANDOUT = "HANDOUT"
private const val RATE = "RATE"
private const val FIX = "FIX"

data class CouponState(
    private val name: String,
    private val description: String,
    private val amountType: String,
    private val type: String,
    private val amount: Int,
    private val id: Long? = null,
) {
    fun toCoupon(): Coupon {
        return when (type) {
            PUBLISHED -> getCouponUsingAmountType(CouponType.IS_PUBLISHED)
            HANDOUT -> getCouponUsingAmountType(CouponType.IS_HAND_OUT)
            else -> throw IllegalArgumentException("유효하지 않는 쿠폰입니다.")
        }
    }

    private fun getCouponUsingAmountType(couponType: CouponType): Coupon {
        return when (amountType) {
            RATE -> Coupon.RateDiscountCoupon(id!!, amount, name, description, couponType)
            FIX -> Coupon.FixDiscountCoupon(id!!, amount, name, description, couponType)
            else -> throw IllegalArgumentException("유효하지 않는 쿠폰입니다.")
        }
    }
}
