package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook

class HandOutCouponBook(
    private val handOutCoupons: CouponBook = CouponBook(),
) {
    fun showHandOutCoupon(): List<Coupon> {
        return handOutCoupons.showCoupons()
    }

    fun addHandOutCoupon(coupon: Coupon) {
        if (handOutCoupons.showCoupons().contains(coupon)) {
            throw IllegalArgumentException("이미 나눠준 쿠폰입니다.")
        }

        handOutCoupons.addCoupon(coupon)
    }
}
