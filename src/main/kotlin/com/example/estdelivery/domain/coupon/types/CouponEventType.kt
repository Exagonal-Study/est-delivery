package com.example.estdelivery.domain.coupon.types

enum class CouponEventType(private val type: String) {

    EVENT("event"),
    SCHEDULE("schedule");

    fun getType(): String {
        return type
    }

}
