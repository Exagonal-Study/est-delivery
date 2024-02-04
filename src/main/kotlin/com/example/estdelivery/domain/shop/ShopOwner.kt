package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon

class ShopOwner(
    private val shop: Shop
) {
    fun handOutCouponToRoyalCustomersInShop(coupon: Coupon) {
        shop.handOutCouponToRoyalCustomers(coupon)
    }

    fun publishCouponInShop(coupon: Coupon) {
        shop.publishCoupon(coupon)
    }

    fun showHandOutCouponInShop(): List<Coupon> {
        return shop.showHandOutCoupon()
    }

    fun showPublishedCouponsInShop(): List<Coupon> {
        return shop.showPublishedCoupons()
    }
}
