package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook

class PublishedCouponBook(
    private val publishedCoupons: CouponBook = CouponBook(),
) {
    fun publishCoupon(coupon: Coupon) {
        if (publishedCoupons.showCoupons().contains(coupon)) {
            throw IllegalArgumentException("이미 게시한 쿠폰입니다.")
        }

        publishedCoupons.addCoupon(coupon)
    }

    fun showPublishedCoupons(): List<Coupon> {
        return publishedCoupons.showCoupons()
    }
}
