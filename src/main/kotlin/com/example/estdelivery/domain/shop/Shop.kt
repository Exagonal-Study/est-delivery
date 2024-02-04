package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.member.Member

class Shop(
    private val shopName: String,
    private val publishedCoupons: CouponBook,
    private val handOutCoupon: CouponBook,
    private val usedCouponBook: CouponBook,
    private val royalCustomers: RoyalCustomers
) {
    fun publishCoupon(coupon: Coupon) {
        publishedCoupons.addCoupon(coupon)
    }

    fun alreadyUsedCoupon(coupon: Coupon): Boolean {
        return usedCouponBook.showCoupons().contains(coupon)
    }

    fun useCoupon(coupon: Coupon) {
        if (usedCouponBook.showCoupons().contains(coupon)) {
            throw IllegalArgumentException("이미 사용한 쿠폰입니다.")
        }

        if (!(publishedCoupons.showCoupons().contains(coupon) || handOutCoupon.showCoupons().contains(coupon))) {
            throw IllegalArgumentException("게시하지 않은 쿠폰입니다.")
        }

        usedCouponBook.addCoupon(coupon)
    }

    fun handOutCouponToRoyalCustomers(coupon: Coupon) {
        royalCustomers.handOutCoupon(coupon)
    }

    fun showPublishedCoupons(): List<Coupon> {
        return publishedCoupons.showCoupons()
    }

    fun showRoyalCustomers(): List<Member> {
        return royalCustomers.showRoyalCustomers()
    }
}
