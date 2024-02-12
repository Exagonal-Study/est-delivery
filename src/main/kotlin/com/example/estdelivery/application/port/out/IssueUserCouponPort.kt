package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.domain.model.Coupon

interface IssueUserCouponPort {
    fun issueUserCoupon(userId: Long, coupon: Coupon)
}
