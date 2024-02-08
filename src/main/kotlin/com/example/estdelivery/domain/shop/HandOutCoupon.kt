package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook

class HandOutCoupon(
    private val handOutCoupons: CouponBook = CouponBook(),
) {
    fun showHandOutCoupon(): List<Coupon> {
        return handOutCoupons.showCoupons()
    }

    fun addHandOutCoupon(coupon: Coupon) {
        handOutCoupons.addCoupon(coupon)
    }
}
