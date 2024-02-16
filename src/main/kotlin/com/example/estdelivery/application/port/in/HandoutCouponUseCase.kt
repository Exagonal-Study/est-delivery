package com.example.estdelivery.application.port.`in`

import com.example.estdelivery.application.port.`in`.command.HandoutCouponCommand

interface HandoutCouponUseCase {
    /**
     * 가게 사장은 단골들에게 쿠폰을 나눠줄 수 있다.
     * @param handoutCouponCommand 나눠줄 쿠폰 정보와 가게 주인 정보
     */
    fun handoutCoupon(handoutCouponCommand: HandoutCouponCommand)
}
