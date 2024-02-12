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

    fun receiveCoupon(coupon: Coupon) {
        usedCouponBook.useCoupon(coupon, CouponBook(publishedCoupons + handOutCouponBook))
    }

    fun addRoyalCustomers(vararg members: Member) {
        royalCustomers.addRoyalCustomers(*members)
    }

    fun handOutCouponToRoyalCustomers(coupon: Coupon) {
        handOutCouponBook.addHandOutCoupon(coupon)
        royalCustomers.handOutCoupon(coupon)
    }

    fun showPublishedCoupons() = publishedCoupons.showPublishedCoupons()

    fun showRoyalCustomers() = royalCustomers.showRoyalCustomers()

    fun showHandOutCoupon() = handOutCouponBook.showHandOutCoupon()

    fun showUsedCoupons() = usedCouponBook.showUsedCoupons()

    fun issueCoupon(coupon: Coupon) = publishedCoupons.issueCoupon(coupon)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Shop

        return id == other.id
    }

    override fun hashCode() = id?.hashCode() ?: 0
}

private operator fun PublishedCouponBook.plus(coupon: HandOutCouponBook) =
    showPublishedCoupons() + coupon.showHandOutCoupon()
