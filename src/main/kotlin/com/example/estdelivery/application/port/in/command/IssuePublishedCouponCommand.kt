package com.example.estdelivery.application.port.`in`.command

data class IssuePublishedCouponCommand(
    val couponId: Long,
    val memberId: Long,
    val shopId: Long,
)
