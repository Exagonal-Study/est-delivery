package com.example.estdelivery.application.dto.coupon

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponQuantity
import com.example.estdelivery.domain.coupon.types.CouponEventType
import com.example.estdelivery.domain.coupon.types.CouponType

data class GenerateCouponCommand(
    val couponName: String,
    val couponType: CouponType,
    val couponEventType: CouponEventType,
    val quantity: Long
) {
    fun toCouponDomain(): Coupon = Coupon(
        name = couponName,
        type = couponType,
        quantity = CouponQuantity(quantity),
        couponEventType = couponEventType
    )

}
