package com.example.estdelivery.adapter.`in`.web.coupon

import com.example.estdelivery.adapter.`in`.web.dto.BaseResponseDto
import com.example.estdelivery.application.dto.coupon.GenerateCouponCommand
import com.example.estdelivery.application.dto.coupon.CouponResponse
import com.example.estdelivery.application.dto.coupon.IssuedCouponCommand
import com.example.estdelivery.application.port.`in`.coupon.CouponWriteUsecase
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class CouponController(
    private val couponWriteUsecase: CouponWriteUsecase
) {
    @PostMapping("/coupons/generate")
    fun generateCoupon(@RequestBody generateCouponCommand: GenerateCouponCommand): BaseResponseDto<CouponResponse> {
        val generateCoupon = couponWriteUsecase.generateCoupon(generateCouponCommand)
        return BaseResponseDto.success(data = generateCoupon)
    }

    @PostMapping("/coupon/issued/{memberId}")
    fun issuedCoupon(@PathVariable memberId: Long, @RequestBody issuedCouponCommand: IssuedCouponCommand): BaseResponseDto<CouponResponse> {
        val useCoupon = couponWriteUsecase.issuedCoupon(memberId, issuedCouponCommand)
        return BaseResponseDto.success(data = useCoupon)
    }

}
