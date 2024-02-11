package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.member.Member

class Shop(
    private val publishedCoupons: PublishedCouponBook,
    private val handOutCouponBook: HandOutCouponBook,
    private val usedCouponBook: UsedCouponBook,
    private val royalCustomers: RoyalCustomers,
    val name: String,
    internal val id: Long? = null,
) {
    fun publishCoupon(coupon: Coupon) {
        publishedCoupons.publishCoupon(coupon)
    }

    fun useCoupon(coupon: Coupon) {
        usedCouponBook.useCoupon(coupon, CouponBook(publishedCoupons + handOutCouponBook))
    }

    fun addRoyalCustomers(vararg members: Member) {
        royalCustomers.addRoyalCustomers(*members)
    }

    fun handOutCouponToRoyalCustomers(coupon: Coupon) {
        handOutCouponBook.addHandOutCoupon(coupon)
        royalCustomers.handOutCoupon(coupon)
    }

    fun showPublishedCoupons(): List<Coupon> {
        return publishedCoupons.showPublishedCoupons()
    }

    fun showRoyalCustomers(): List<Member> {
        return royalCustomers.showRoyalCustomers()
    }

    fun showHandOutCoupon(): List<Coupon> {
        return handOutCouponBook.showHandOutCoupon()
    }

    fun showUsedCoupons(): List<Coupon> {
        return usedCouponBook.showUsedCoupons()
    }

    fun issueCoupon(coupon: Coupon): Coupon {
        return publishedCoupons.issueCoupon(coupon)
    }
}

private operator fun PublishedCouponBook.plus(coupon: HandOutCouponBook): List<Coupon> {
    return showPublishedCoupons() + coupon.showHandOutCoupon()
}
