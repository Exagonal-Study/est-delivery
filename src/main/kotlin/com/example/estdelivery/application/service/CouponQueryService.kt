package com.example.estdelivery.application.service

import com.example.estdelivery.application.dto.coupon.CouponsResponse
import com.example.estdelivery.application.port.`in`.coupon.CouponQueryUseCase
import com.example.estdelivery.application.port.out.coupon.GetCouponsQueryPort
import org.springframework.stereotype.Service

@Service
class CouponQueryService(
    private val getCouponsQueryPort: GetCouponsQueryPort
) : CouponQueryUseCase {
    override fun getCoupons(): List<CouponsResponse> {
        return getCouponsQueryPort.getCoupons()
            .map {
                CouponsResponse(
                    it.id!!,
                    it.name,
                    it.type,
                    it.discountValue,
                    it.expiryDate
                )
            }
    }
}