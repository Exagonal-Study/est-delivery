package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.member.Member

class ShopOwner(
    private val shop: Shop,
    internal val id: Long? = null,
) {
    fun handOutCouponToRoyalCustomersInShop(coupon: Coupon) = shop.handOutCouponToRoyalCustomers(coupon)
    fun publishCouponInShop(coupon: Coupon) = shop.publishCoupon(coupon)
    fun addRoyalCustomersInShop(vararg members: Member) = shop.addRoyalCustomers(*members)
    fun receiveCoupon(coupon: Coupon) = shop.receiveCoupon(coupon)
    fun showHandOutCouponInShop() = shop.showHandOutCoupon()
    fun showPublishedCouponsInShop() = shop.showPublishedCoupons()
    fun showRoyalCustomersInShop() = shop.showRoyalCustomers()
    fun showShop() = shop
    fun showUsedCouponBook() = shop.showUsedCoupons()
    fun issuePublishedCouponInShop(coupon: Coupon) = shop.issueCoupon(coupon)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShopOwner

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
