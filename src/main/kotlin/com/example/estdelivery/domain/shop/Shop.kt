package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.member.Member

class Shop(
    private val publishedCoupons: PublishedCouponBook,
    private val handOutCoupon: HandOutCoupon,
    private val usedCouponBook: UsedCouponBook,
    private val royalCustomers: RoyalCustomers
) {
    fun publishCoupon(coupon: Coupon) {
        publishedCoupons.publishCoupon(coupon)
    }

    fun useCoupon(coupon: Coupon) {
        usedCouponBook.useCoupon(coupon, CouponBook(publishedCoupons + handOutCoupon))
    }

    fun addRoyalCustomers(vararg members: Member) {
        royalCustomers.addRoyalCustomers(*members)
    }

    fun handOutCouponToRoyalCustomers(coupon: Coupon) {
        handOutCoupon.addHandOutCoupon(coupon)
        royalCustomers.handOutCoupon(coupon)
    }

    fun showPublishedCoupons(): List<Coupon> {
        return publishedCoupons.showPublishedCoupons()
    }

    fun showRoyalCustomers(): List<Member> {
        return royalCustomers.showRoyalCustomers()
    }

    fun showHandOutCoupon(): List<Coupon> {
        return handOutCoupon.showHandOutCoupon()
    }
}

private operator fun PublishedCouponBook.plus(coupon: HandOutCoupon): List<Coupon> {
    return showPublishedCoupons() + coupon.showHandOutCoupon()
}
