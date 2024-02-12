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
    fun isOwn(shop: Shop) = this.shop == shop
    fun showUsedCouponBook() = shop.showUsedCoupons()
}
