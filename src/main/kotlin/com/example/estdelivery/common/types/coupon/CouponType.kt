package com.example.estdelivery.common.types.coupon

import java.util.Locale

enum class CouponType {
    RATE, AMOUNT;

    val value: String
        get() = name.lowercase(Locale.getDefault())
}