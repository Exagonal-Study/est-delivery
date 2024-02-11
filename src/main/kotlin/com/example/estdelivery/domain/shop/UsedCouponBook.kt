package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook

class UsedCouponBook(
    private val usedCouponBook: CouponBook = CouponBook(),
) {
    fun useCoupon(coupon: Coupon, shopCouponBook: CouponBook) {
        if (usedCouponBook.showCoupons().contains(coupon)) {
            throw IllegalArgumentException("이미 사용한 쿠폰입니다.")
        }

        if (!shopCouponBook.showCoupons().contains(coupon)) {
            throw IllegalArgumentException("게시하지 않은 쿠폰입니다.")
        }

        usedCouponBook.addCoupon(coupon)
    }

    fun showUsedCoupons(): List<Coupon> {
        return usedCouponBook.showCoupons()
    }
}