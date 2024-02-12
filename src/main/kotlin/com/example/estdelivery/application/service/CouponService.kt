package com.example.estdelivery.application.service

import com.example.estdelivery.adapters.`in`.web.coupon.dto.request.CouponCommand
import com.example.estdelivery.adapters.`in`.web.coupon.dto.request.toCreateDomain
import com.example.estdelivery.application.dto.coupon.CreateCouponResponse
import com.example.estdelivery.application.port.`in`.coupon.CreateCouponUseCase
import com.example.estdelivery.application.port.out.coupon.CouponPersistencePort
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val couponPersistencePort: CouponPersistencePort
) : CreateCouponUseCase {
    override fun createCoupon(command: CouponCommand): CreateCouponResponse {
        val coupon = command.toCreateDomain()
        val savedCoupon = couponPersistencePort.save(coupon)
        return CreateCouponResponse(
            name = savedCoupon.name,
            type = savedCoupon.type,
            discountValue = savedCoupon.discountValue,
            expiryDate = savedCoupon.expiryDate
        )
    }
}