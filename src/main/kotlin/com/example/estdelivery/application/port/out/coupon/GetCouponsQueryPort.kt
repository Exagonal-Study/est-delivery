package com.example.estdelivery.application.port.out.coupon

import com.example.estdelivery.domain.coupon.Coupon

interface GetCouponsQueryPort {
    fun getCoupons(): List<Coupon>
}