package com.example.estdelivery.adapters.`in`.web.coupon.controller

import com.example.estdelivery.adapters.`in`.web.coupon.dto.request.CreateCouponRequest
import com.example.estdelivery.application.dto.coupon.CreateCouponResponse
import com.example.estdelivery.application.port.`in`.coupon.CreateCouponUseCase
import com.example.estdelivery.application.port.`in`.coupon.IssueCouponUseCase
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupons")
class CouponController(
    private val createCouponUseCase: CreateCouponUseCase,
    private val issueCouponUseCase: IssueCouponUseCase,
) {
    @PostMapping
    fun createCoupon(@RequestBody request: CreateCouponRequest): CreateCouponResponse {
        return createCouponUseCase.createCoupon(request)
    }

    @PostMapping("/{couponId}/issue")
    fun issueCoupon(@PathVariable couponId: Long, @RequestParam memberId: Long) {
        issueCouponUseCase.issueCoupon(memberId, couponId)
    }
}