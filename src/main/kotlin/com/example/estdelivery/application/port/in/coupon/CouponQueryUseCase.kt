package com.example.estdelivery.application.port.`in`.coupon

import com.example.estdelivery.application.dto.coupon.CouponsResponse

interface CouponQueryUseCase {
    fun getCoupons(): List<CouponsResponse>
}