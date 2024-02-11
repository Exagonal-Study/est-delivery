package com.example.estdelivery.application.port.out

import com.example.estdelivery.domain.coupon.Coupon

interface CouponPersistencePort {

    fun saveCoupon(coupon: Coupon): Coupon

    fun updateCouponQuantity(coupon: Coupon)

    fun findCouponById(couponId: Long): Coupon

}
