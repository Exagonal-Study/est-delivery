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
        require(handOutCoupons.showCoupons().contains(coupon).not()) {"이미 나눠준 쿠폰입니다."}
        require(coupon.isHandOut()) {"나눠줄 수 없는 쿠폰입니다."}
        handOutCoupons.addCoupon(coupon)
    }
}
