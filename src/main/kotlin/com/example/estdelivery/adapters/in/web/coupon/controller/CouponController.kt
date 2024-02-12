package com.example.estdelivery.adapters.`in`.web.coupon.controller

import com.example.estdelivery.adapters.`in`.web.coupon.dto.request.CreateCouponRequest
import com.example.estdelivery.adapters.`in`.web.coupon.dto.request.IssueCouponRequest
import com.example.estdelivery.application.dto.coupon.CouponsResponse
import com.example.estdelivery.application.port.`in`.coupon.CouponQueryUseCase
import com.example.estdelivery.application.port.`in`.coupon.CreateCouponUseCase
import com.example.estdelivery.application.port.`in`.coupon.IssueCouponUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupons")
class CouponController(
    private val createCouponUseCase: CreateCouponUseCase,
    private val issueCouponUseCase: IssueCouponUseCase,
    private val couponQueryUseCase: CouponQueryUseCase
) {
    @PostMapping
    fun createCoupon(@RequestBody request: CreateCouponRequest) {
        createCouponUseCase.createCoupon(request)
    }

    @GetMapping
    fun getCoupons(): List<CouponsResponse> {
        return couponQueryUseCase.getCoupons()
    }

    @PostMapping("/{couponId}/issue")
    fun issueCoupon(@PathVariable couponId: Long, @RequestBody request: IssueCouponRequest) {
        issueCouponUseCase.issueCoupon(request.memberId, couponId)
    }
}