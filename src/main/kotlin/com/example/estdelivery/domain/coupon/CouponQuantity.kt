package com.example.estdelivery.domain.coupon

@JvmInline
value class CouponQuantity(
    val quantity: Long
) {
    init {
        require(quantity > 0) { throw IllegalArgumentException("쿠폰 수량이 소진되었습니다.") }
    }
}
