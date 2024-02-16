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
        require(coupon.isHandOut()) { "나눠줄 수 없는 쿠폰입니다." }
        if (handOutCoupons.showCoupons().contains(coupon)) {
            return
        }
        handOutCoupons.addCoupon(coupon)
    }
}
