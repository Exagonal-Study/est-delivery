package com.example.estdelivery.domain.member

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook

class Member(
    val name: String,
    private val unUsedCouponBook: CouponBook
) {
    fun useCoupon(coupon: Coupon) {
        unUsedCouponBook.deleteCoupon(coupon)
    }

    fun showMyCouponBook(): CouponBook {
        return unUsedCouponBook
    }

    fun receiveCoupon(coupon: Coupon) {
        unUsedCouponBook.addCoupon(coupon)
    }
}
