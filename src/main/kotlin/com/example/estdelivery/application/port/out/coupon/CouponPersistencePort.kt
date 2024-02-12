package com.example.estdelivery.application.port.out.coupon

import com.example.estdelivery.domain.coupon.Coupon

interface CouponPersistencePort {
    fun save(coupon: Coupon): Coupon
//    fun findById(couponId: String): Coupon?
//    fun findByUserIdAndDate(userId: String, date: LocalDate): List<Coupon>
}