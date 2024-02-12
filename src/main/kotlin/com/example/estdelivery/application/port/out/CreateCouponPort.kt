package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.domain.model.Coupon

interface CreateCouponPort {
    fun createCoupon(coupon: Coupon): Coupon
}
