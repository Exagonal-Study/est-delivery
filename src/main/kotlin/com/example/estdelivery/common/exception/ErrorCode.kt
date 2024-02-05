package com.example.estdelivery.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val code: Int,
    val status: HttpStatus,
    val message: String,
) {
    DUPLICATED_COUPON(1000, HttpStatus.BAD_REQUEST, "duplicated coupon."),
    NOT_FOUND_COUPON_CONFIG(1001, HttpStatus.NOT_FOUND, "not found coupon config.")
    ;
}
