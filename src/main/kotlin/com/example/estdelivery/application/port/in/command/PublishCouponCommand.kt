package com.example.estdelivery.application.port.`in`.command

import com.example.estdelivery.domain.coupon.Coupon

data class PublishCouponCommand(
    val shopOwnerId: Long,
    val shopId: Long,
    val coupon: Coupon,
)
