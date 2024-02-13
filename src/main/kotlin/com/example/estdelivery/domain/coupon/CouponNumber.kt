package com.example.estdelivery.domain.coupon

@JvmInline
value class CouponNumber(
    val number: String
) {
    init {
        require(number.isNotEmpty()) { throw IllegalArgumentException("쿠폰 번호가 입력되지 않았습니다.") }
    }
}
