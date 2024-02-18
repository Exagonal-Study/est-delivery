package com.example.estdelivery.application.domain.service

import com.example.estdelivery.adapter.`in`.web.dto.UserCouponResponse
import com.example.estdelivery.application.port.`in`.FindUserCouponUseCase
import com.example.estdelivery.application.port.out.FindUserCouponPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FindUserCouponService(
    val findUserCouponPort: FindUserCouponPort
) : FindUserCouponUseCase {
    override fun findUserCoupons(userId: Long): List<UserCouponResponse> =
        findUserCouponPort.findByUserId(userId).map { UserCouponResponse(it.code.get(), it.configId) }
}