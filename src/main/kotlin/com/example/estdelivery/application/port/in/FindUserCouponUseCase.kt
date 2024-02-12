package com.example.estdelivery.application.port.`in`

import com.example.estdelivery.adapter.`in`.web.dto.UserCouponResponse

interface FindUserCouponUseCase {
    fun findUserCoupon(userId: Long): List<UserCouponResponse>
}
