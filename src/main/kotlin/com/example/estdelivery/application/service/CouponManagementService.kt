package com.example.estdelivery.application.service

import com.example.estdelivery.adapters.`in`.web.coupon.dto.request.CreateCouponRequest
import com.example.estdelivery.adapters.`in`.web.coupon.dto.request.toDomain
import com.example.estdelivery.application.port.`in`.coupon.CreateCouponUseCase
import com.example.estdelivery.application.port.`in`.coupon.IssueCouponUseCase
import com.example.estdelivery.application.port.out.coupon.CouponPersistencePort
import org.springframework.stereotype.Service

@Service
class CouponManagementService(
    private val couponPersistencePort: CouponPersistencePort,
) : CreateCouponUseCase, IssueCouponUseCase {
    override fun createCoupon(request: CreateCouponRequest) {
        val coupon = request.toDomain()
        couponPersistencePort.createCoupon(coupon)
    }

    override fun issueCoupon(memberId: Long, couponId: Long) {
        couponPersistencePort.issueCoupon(memberId, couponId)
    }
}