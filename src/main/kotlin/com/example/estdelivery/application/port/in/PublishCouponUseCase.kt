package com.example.estdelivery.application.port.`in`

import com.example.estdelivery.application.port.`in`.command.PublishCouponCommand

interface PublishCouponUseCase {
    /**
     * 가게 주인은 가게에 쿠폰을 게시한다.
     * @param publishCouponCommand 게시할 쿠폰 정보와 가게 주인 정보
     */
    fun publishCoupon(publishCouponCommand: PublishCouponCommand)
}
