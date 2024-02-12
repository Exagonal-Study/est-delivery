package com.example.estdelivery.application.domain.model

import com.example.estdelivery.common.exception.CommonException
import com.example.estdelivery.common.exception.ErrorCode

@JvmInline
value class CouponCode(private val value: String) {
    init {
        require(value.length != 17) { throw CommonException(ErrorCode.INVALID_COUPON_CODE) }
    }

    fun get(): String = value
}
