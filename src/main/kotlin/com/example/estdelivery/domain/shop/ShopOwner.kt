package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.member.Member

class ShopOwner(
    private val shop: Shop
) {
    fun handOutCouponToRoyalCustomersInShop(coupon: Coupon) {
        shop.handOutCouponToRoyalCustomers(coupon)
    }

    fun publishCouponInShop(coupon: Coupon) {
        shop.publishCoupon(coupon)
    }

    fun addRoyalCustomersInShop(vararg members: Member) {
        shop.addRoyalCustomers(*members)
    }

    fun showHandOutCouponInShop(): List<Coupon> {
        return shop.showHandOutCoupon()
    }

    fun showPublishedCouponsInShop(): List<Coupon> {
        return shop.showPublishedCoupons()
    }

    fun showRoyalCustomersInShop(): List<Member> {
        return shop.showRoyalCustomers()
    }
}
