package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.port.out.state.CouponState

interface CreateCouponStatePort {
    fun create(couponState: CouponState): CouponState
}
