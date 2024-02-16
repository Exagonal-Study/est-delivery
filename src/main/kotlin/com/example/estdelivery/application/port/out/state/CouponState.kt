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
            RATE -> Coupon.RateDiscountCoupon(amount, name, description, getCouponType(type), id)
            FIX -> Coupon.FixDiscountCoupon(amount, name, description, getCouponType(type), id)
        }
    }

    companion object {
        fun from(coupon: Coupon): CouponState {
            return CouponState(
                coupon.name,
                coupon.description,
                getCouponStateAmountType(coupon),
                getCouponStateType(coupon),
                getCouponAmount(coupon),
                coupon.id
            )
        }

        private fun getCouponAmount(coupon: Coupon): Int {
            return when (coupon) {
                is Coupon.RateDiscountCoupon -> coupon.discountRate
                is Coupon.FixDiscountCoupon -> coupon.discountAmount
            }
        }

        private fun getCouponStateType(coupon: Coupon): CouponStateType {
            return when (coupon.couponType) {
                IS_PUBLISHED -> PUBLISHED
                IS_HAND_OUT -> HANDOUT
            }
        }

        private fun getCouponStateAmountType(coupon: Coupon): CouponStateAmountType {
            return when (coupon) {
                is Coupon.RateDiscountCoupon -> RATE
                is Coupon.FixDiscountCoupon -> FIX
            }
        }
    }

    private fun getCouponType(type: CouponStateType) = when (type) {
        PUBLISHED -> IS_PUBLISHED
        HANDOUT -> IS_HAND_OUT
    }
}
