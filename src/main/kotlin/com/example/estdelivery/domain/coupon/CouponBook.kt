package com.example.estdelivery.domain.coupon

class CouponBook(
    private var coupons: List<Coupon> = listOf()
) {
    fun showCoupons(): List<Coupon> {
        return coupons.toList()
    }

    fun deleteCoupon(coupon: Coupon) {
        if (!coupons.contains(coupon)) {
            throw IllegalArgumentException("존재하지 않는 쿠폰입니다.")
        }
        coupons = coupons - coupon
    }

    fun addCoupon(coupon: Coupon) {
        if (coupons.contains(coupon)) {
            throw IllegalArgumentException("이미 존재하는 쿠폰입니다.")
        }
        coupons = coupons + coupon
    }

    operator fun plus(addedCoupons: CouponBook): List<Coupon> {
        return coupons + addedCoupons.showCoupons()
    }
}
