package com.example.estdelivery.domain.coupon

import java.time.LocalDateTime

class CouponLog(
    val memberId: Long,
    val couponId: Long,
    val couponNumber: CouponNumber,
    val couponQuantity: CouponQuantity = CouponQuantity(1),
    val createdDate: LocalDateTime? = null
) {

    fun getCouponQuantity(): Long = couponQuantity.quantity
}
