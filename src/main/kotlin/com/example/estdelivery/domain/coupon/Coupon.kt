package com.example.estdelivery.domain.coupon

import com.example.estdelivery.domain.coupon.types.CouponEventType
import com.example.estdelivery.domain.coupon.types.CouponType
import java.time.LocalDateTime

class Coupon(
    val name: String,
    val quantity: CouponQuantity,
    val type: CouponType,
    val couponEventType: CouponEventType,
    val createdDate: LocalDateTime? = null,
) {
    init {
        require(name.isNotEmpty()) { throw IllegalArgumentException("쿠폰 이름이 입력되지 않았습니다.") }
        require(name.length <= 80) { throw IllegalArgumentException("쿠폰 이름은 80자를 넘을 수 없습니다.") }
    }

    fun getCouponQuantity(): Long {
        return quantity.quantity
    }

}
