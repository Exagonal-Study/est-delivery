package com.example.estdelivery.application.port.out

import com.example.estdelivery.domain.coupon.Coupon

interface CouponPersistencePort {

    fun generateCoupon(coupon: Coupon): Coupon

    fun findCouponById(couponId: Long): Coupon
}
