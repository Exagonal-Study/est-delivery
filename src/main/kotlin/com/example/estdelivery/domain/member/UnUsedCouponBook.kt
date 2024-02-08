package com.example.estdelivery.domain.member

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook

class UnUsedCouponBook(
    private val unUsedCouponBook: CouponBook = CouponBook(),
) {
    fun showUnUsedCoupons(): List<Coupon> {
        return unUsedCouponBook.showCoupons()
    }

    fun addUnUsedCoupon(coupon: Coupon) {
        unUsedCouponBook.addCoupon(coupon)
    }

    fun deleteUnUsedCoupon(coupon: Coupon) {
        unUsedCouponBook.deleteCoupon(coupon)
    }
}
