package com.example.estdelivery.domain.member

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook

class UnusedCouponBook(
    private val unUsedCouponBook: CouponBook = CouponBook(),
) {
    fun showUnusedCoupons(): List<Coupon> {
        return unUsedCouponBook.showCoupons()
    }

    fun addUnusedCoupon(coupon: Coupon) {
        if (unUsedCouponBook.showCoupons().contains(coupon)) {
            throw IllegalArgumentException("이미 존재하는 쿠폰입니다.")
        }

        unUsedCouponBook.addCoupon(coupon)
    }

    fun removeUsedCoupon(coupon: Coupon) {
        if (!unUsedCouponBook.showCoupons().contains(coupon)) {
            throw IllegalArgumentException("사용할 수 없는 쿠폰입니다.")
        }

        unUsedCouponBook.deleteCoupon(coupon)
    }
}
