package com.example.estdelivery.application.port.out

import com.example.estdelivery.domain.coupon.Coupon

interface LoadCouponStatePort {
    fun exists(couponId: Long): Boolean
    fun findById(couponId: Long): Coupon
}
