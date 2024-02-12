package com.example.estdelivery.application.port.out.coupon

import com.example.estdelivery.domain.coupon.Coupon

interface CouponQueryPort {
    fun getCoupons(): List<Coupon>
    fun getCoupon(couponId: Long): Coupon
}