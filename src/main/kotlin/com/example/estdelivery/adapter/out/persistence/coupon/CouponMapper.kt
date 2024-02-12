package com.example.estdelivery.adapter.out.persistence.coupon

import com.example.estdelivery.adapter.out.persistence.user.UserCouponEntity
import com.example.estdelivery.application.domain.model.Coupon


object CouponMapper {
    fun toCoupon(couponEntity: CouponEntity): Coupon =
        Coupon(id = couponEntity.id!!, code = couponEntity.code, configId = couponEntity.configId)

    fun toCoupon(userCouponEntity: UserCouponEntity): Coupon =
        Coupon(code = userCouponEntity.couponCode, configId = userCouponEntity.couponConfigId)

    fun toCouponEntity(coupon: Coupon): CouponEntity = CouponEntity(code = coupon.code, configId = coupon.configId)

    fun toUserCouponEntity(userId: Long, coupon: Coupon): UserCouponEntity =
        UserCouponEntity(couponCode = coupon.code, couponConfigId = coupon.configId, userId = userId)
}
