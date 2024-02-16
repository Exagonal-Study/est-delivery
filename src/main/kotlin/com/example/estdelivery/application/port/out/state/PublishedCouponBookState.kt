package com.example.estdelivery.application.port.out.state

import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.shop.PublishedCouponBook

data class PublishedCouponBookState(
    private val coupons: List<CouponState>
) {
    fun toPublishedCouponBook(): PublishedCouponBook {
        return PublishedCouponBook(
            CouponBook(
                coupons.map {

                    it.toCoupon()
                }
            )
        )
    }
}
