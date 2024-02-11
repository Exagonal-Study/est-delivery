package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.port.out.state.PublishedCouponBookState

interface LoadPublishedCouponBookStatePort {
    fun exitsById(couponId: Long): Boolean
    fun findById(couponId: Long): PublishedCouponBookState
}