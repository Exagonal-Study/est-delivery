package com.example.estdelivery.application.port.`in`.coupon

import com.example.estdelivery.application.dto.coupon.GenerateCouponCommand
import com.example.estdelivery.application.dto.coupon.CouponResponse
import com.example.estdelivery.application.dto.coupon.IssuedCouponCommand

interface CouponWriteUsecase {

    fun generateCoupon(generateCouponCommand: GenerateCouponCommand): CouponResponse

    fun issuedCoupon(memberId: Long, issuedCouponCommand: IssuedCouponCommand): CouponResponse
}
