package com.example.estdelivery.application.domain.model

import com.example.estdelivery.common.Constant.COUPON_CODE_LENGTH
import com.example.estdelivery.common.exception.CommonException
import com.example.estdelivery.common.exception.ErrorCode

@JvmInline
value class CouponCode(private val value: String) {
    init {
        require(value.length == COUPON_CODE_LENGTH) { throw CommonException(ErrorCode.INVALID_COUPON_CODE) }
    }

    fun get(): String = value
}
