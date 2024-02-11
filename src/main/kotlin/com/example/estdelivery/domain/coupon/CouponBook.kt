package com.example.estdelivery.domain.coupon

class CouponBook(
    private var coupons: List<Coupon> = listOf()
) {
    fun showCoupons(): List<Coupon> {
        return coupons.toList()
    }

    fun deleteCoupon(coupon: Coupon) {
        require(coupons.contains(coupon)) { "존재하지 않는 쿠폰입니다." }
        coupons = coupons - coupon
    }

    fun addCoupon(coupon: Coupon) {
        require(coupons.contains(coupon).not()) { "이미 존재하는 쿠폰입니다." }
        coupons = coupons + coupon
    }
}
