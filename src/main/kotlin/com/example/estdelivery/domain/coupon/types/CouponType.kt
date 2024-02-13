package com.example.estdelivery.domain.coupon.types

enum class CouponType(private val type: String) {

    // 할인율 쿠폰
    PERCENTAGE("percentage"),

    // 금액제 쿠폰
    FIXED("fixed");

    fun getType(): String {
        return type
    }

}
