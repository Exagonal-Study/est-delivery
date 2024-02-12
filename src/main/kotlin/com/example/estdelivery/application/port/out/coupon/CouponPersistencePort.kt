package com.example.estdelivery.application.port.out.coupon

import com.example.estdelivery.domain.coupon.Coupon

interface CouponPersistencePort {
    fun createCoupon(coupon: Coupon): Coupon
    fun issueCoupon(memberId: Long, couponId: Long)
}