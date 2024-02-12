package com.example.estdelivery.application.port.`in`.coupon

import com.example.estdelivery.adapters.`in`.web.coupon.dto.request.CreateCouponRequest

interface CreateCouponUseCase {
    fun createCoupon(request: CreateCouponRequest)
}