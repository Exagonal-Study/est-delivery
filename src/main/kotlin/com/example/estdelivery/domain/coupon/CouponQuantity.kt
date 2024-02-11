package com.example.estdelivery.domain.coupon

@JvmInline
value class CouponQuantity(
    val quantity: Long
) {
    init {
        require(quantity > 0) { throw IllegalArgumentException("쿠폰 수량은 0보다 작을 수 없습니다.") }
    }

    fun minus() = CouponQuantity(quantity - MIN_TARGET_QUANTITY)

    companion object {
        private const val MIN_TARGET_QUANTITY = 1L
    }

}
