package com.example.estdelivery.application.port.`in`.coupon

import com.example.estdelivery.adapters.`in`.web.coupon.dto.request.CreateCouponRequest
import com.example.estdelivery.application.dto.coupon.CreateCouponResponse

interface CreateCouponUseCase {
    fun createCoupon(request: CreateCouponRequest): CreateCouponResponse
}