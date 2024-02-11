package com.example.estdelivery.application.port.out.state

import com.example.estdelivery.application.port.out.state.CouponStateAmountType.FIX
import com.example.estdelivery.application.port.out.state.CouponStateAmountType.RATE
import com.example.estdelivery.application.port.out.state.CouponStateType.HANDOUT
import com.example.estdelivery.application.port.out.state.CouponStateType.PUBLISHED
import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponType.IS_HAND_OUT
import com.example.estdelivery.domain.coupon.CouponType.IS_PUBLISHED

data class CouponState(
    private val name: String,
    private val description: String,
    private val amountType: CouponStateAmountType,
    private val type: CouponStateType,
    private val amount: Int,
    private val id: Long? = null,
) {
    fun toCoupon(): Coupon {
        return when (amountType) {
            RATE -> Coupon.RateDiscountCoupon(id!!, amount, name, description, getCouponType(type))
            FIX -> Coupon.FixDiscountCoupon(id!!, amount, name, description, getCouponType(type))
        }
    }

    private fun getCouponType(type: CouponStateType) = when (type) {
        PUBLISHED -> IS_PUBLISHED
        HANDOUT -> IS_HAND_OUT
    }
}
