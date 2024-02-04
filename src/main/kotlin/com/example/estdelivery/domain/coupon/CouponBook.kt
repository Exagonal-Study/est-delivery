package com.example.estdelivery.domain.coupon

class CouponBook(
    private val coupons: MutableList<Coupon> = mutableListOf()
) {
    fun deleteCoupon(coupon: Coupon) {
        if (!coupons.contains(coupon)) {
            throw IllegalArgumentException("존재하지 않는 쿠폰입니다.")
        }
        coupons.remove(coupon)
    }

    fun addCoupon(coupon: Coupon) {
        if (coupons.contains(coupon)) {
            throw IllegalArgumentException("이미 존재하는 쿠폰입니다.")
        }
        coupons.add(coupon)
    }
}
