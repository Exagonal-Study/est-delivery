package com.example.estdelivery.application.port.out.state

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.domain.shop.HandOutCouponBook
import com.example.estdelivery.domain.shop.PublishedCouponBook
import com.example.estdelivery.domain.shop.RoyalCustomers
import com.example.estdelivery.domain.shop.Shop
import com.example.estdelivery.domain.shop.UsedCouponBook

data class ShopState(
    private val name: String,
    private val publishedCoupons: List<Coupon>,
    private val handoutCoupons: List<Coupon>,
    private val usedCoupons: List<Coupon>,
    private val royalCustomers: List<Member>,
    private val id: Long? = null,
) {
    fun toShop(): Shop {
        return Shop(
            PublishedCouponBook(CouponBook(publishedCoupons)),
            HandOutCouponBook(CouponBook(handoutCoupons)),
            UsedCouponBook(CouponBook(usedCoupons)),
            RoyalCustomers(royalCustomers),
            name,
            id
        )
    }

    companion object {
        fun from(shop: Shop): ShopState {
            return ShopState(
                shop.name,
                shop.showPublishedCoupons().map { it },
                shop.showHandOutCoupon().map { it },
                shop.showUsedCoupons().map { it },
                shop.showRoyalCustomers().map { it },
                shop.id
            )
        }
    }
}
