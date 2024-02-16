package com.example.estdelivery.application.port.out

import com.example.estdelivery.domain.coupon.Coupon

interface CreateCouponStatePort {
    fun create(coupon: Coupon): Coupon
}
