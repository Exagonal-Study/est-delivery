package com.example.estdelivery.adapters.`in`.web.coupon.controller

import com.example.estdelivery.adapters.`in`.web.coupon.dto.request.CouponCommand
import com.example.estdelivery.application.dto.coupon.CreateCouponResponse
import com.example.estdelivery.application.port.`in`.coupon.CreateCouponUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupons")
class CouponController(
    private val createCouponUseCase: CreateCouponUseCase
) {
    @PostMapping
    fun createCoupon(@RequestBody command: CouponCommand): CreateCouponResponse {
        return createCouponUseCase.createCoupon(command)
    }
}