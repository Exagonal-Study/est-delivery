package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.domain.model.Coupon

interface FindUserCouponPort {
    fun existsIssuedUserCoupon(userId: Long, coupon: Coupon): Boolean

    fun findByUserId(userId: Long): List<Coupon>
}
