package com.example.estdelivery.adapter.out.persistence.coupon

import com.example.estdelivery.adapter.out.persistence.user.UserCouponEntity
import com.example.estdelivery.application.domain.model.Coupon
import com.example.estdelivery.application.domain.model.CouponCode


object CouponMapper {
    fun toCoupon(couponEntity: CouponEntity): Coupon =
        Coupon(id = couponEntity.id!!, code = CouponCode(couponEntity.code), configId = couponEntity.configId)

    fun toCoupon(userCouponEntity: UserCouponEntity): Coupon =
        Coupon(code = CouponCode(userCouponEntity.couponCode), configId = userCouponEntity.couponConfigId)

    fun toCouponEntity(coupon: Coupon): CouponEntity =
        CouponEntity(code = coupon.code.get(), configId = coupon.configId)

    fun toUserCouponEntity(userId: Long, coupon: Coupon): UserCouponEntity =
        UserCouponEntity(couponCode = coupon.code.get(), couponConfigId = coupon.configId, userId = userId)
}
