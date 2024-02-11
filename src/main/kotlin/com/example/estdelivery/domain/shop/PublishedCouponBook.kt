package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook

class PublishedCouponBook(
    private val publishedCoupons: CouponBook = CouponBook(),
) {
    fun publishCoupon(coupon: Coupon) {
        require(publishedCoupons.showCoupons().contains(coupon).not()) { "이미 게시한 쿠폰입니다." }
        require(coupon.isPublished()) { "게시할 수 없는 쿠폰입니다." }
        publishedCoupons.addCoupon(coupon)
    }

    fun showPublishedCoupons(): List<Coupon> {
        return publishedCoupons.showCoupons()
    }

    fun issueCoupon(coupon: Coupon): Coupon {
        require(publishedCoupons.showCoupons().contains(coupon)) { "게시된 쿠폰이 아닙니다." }
        return publishedCoupons.showCoupons().find { it == coupon }!!
    }
}
