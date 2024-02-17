package com.example.estdelivery.application.port.out.persistence.mapper

import com.example.estdelivery.application.port.out.persistence.entity.CouponEntity
import com.example.estdelivery.application.port.out.persistence.entity.CouponStateAmountType
import com.example.estdelivery.application.port.out.persistence.entity.CouponStateAmountType.FIX
import com.example.estdelivery.application.port.out.persistence.entity.CouponStateAmountType.RATE
import com.example.estdelivery.application.port.out.persistence.entity.CouponStateType
import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponType

internal fun toCoupon(entity: CouponEntity): Coupon {
    return when (entity.amountType) {
        RATE -> Coupon.RateDiscountCoupon(
            entity.amount,
            entity.name,
            entity.description,
            getCouponType(entity.type),
            entity.id
        )

        FIX -> Coupon.FixDiscountCoupon(
            entity.amount,
            entity.name,
            entity.description,
            getCouponType(entity.type),
            entity.id
        )
    }
}

internal fun fromCoupon(coupon: Coupon): CouponEntity {
    return CouponEntity(
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
        CouponType.IS_PUBLISHED -> CouponStateType.PUBLISHED
        CouponType.IS_HAND_OUT -> CouponStateType.HANDOUT
    }
}

private fun getCouponStateAmountType(coupon: Coupon): CouponStateAmountType {
    return when (coupon) {
        is Coupon.RateDiscountCoupon -> RATE
        is Coupon.FixDiscountCoupon -> FIX
    }
}

private fun getCouponType(type: CouponStateType) = when (type) {
    CouponStateType.PUBLISHED -> CouponType.IS_PUBLISHED
    CouponStateType.HANDOUT -> CouponType.IS_HAND_OUT
}
