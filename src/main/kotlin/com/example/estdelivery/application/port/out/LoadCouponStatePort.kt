package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.port.out.state.CouponState
import com.example.estdelivery.domain.member.Member

interface LoadCouponStatePort {
    fun findByCouponId(couponId: Long): CouponState
}