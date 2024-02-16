package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.port.out.state.CouponState

interface LoadCouponStatePort {
    fun findByCouponId(couponId: Long): CouponState
    fun exists(couponId: Long): Boolean
    fun findById(couponId: Long): CouponState
}