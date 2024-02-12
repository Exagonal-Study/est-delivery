package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook

class UsedCouponBook(
    private val usedCouponBook: CouponBook = CouponBook(),
) {
    fun useCoupon(coupon: Coupon, shopCouponBook: CouponBook) {
        require(usedCouponBook.showCoupons().contains(coupon).not()) { "이미 사용한 쿠폰입니다." }
        require(shopCouponBook.showCoupons().contains(coupon)) { "게시하거나 나눠주지 않은 쿠폰입니다." }
        usedCouponBook.addCoupon(coupon)
    }

    fun showUsedCoupons(): List<Coupon> {
        return usedCouponBook.showCoupons()
    }
}