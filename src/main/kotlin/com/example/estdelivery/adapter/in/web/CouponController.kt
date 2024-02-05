package com.example.estdelivery.adapter.`in`.web

import com.example.estdelivery.adapter.`in`.web.dto.ApiResponse
import com.example.estdelivery.adapter.`in`.web.dto.CouponIssueRequest
import com.example.estdelivery.adapter.`in`.web.dto.UserCouponResponse
import com.example.estdelivery.application.port.`in`.FindUserCouponUseCase
import com.example.estdelivery.application.port.`in`.IssueCouponCommand
import com.example.estdelivery.application.port.`in`.IssueCouponUseCase
import com.example.estdelivery.common.Constant.X_AUTHENTICATED_USER
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupons")
class CouponController(
    private val issueCouponUseCase: IssueCouponUseCase,
    private val findUserCouponUseCase: FindUserCouponUseCase
) {
    @PostMapping
    fun issueCoupon(
        @RequestHeader(X_AUTHENTICATED_USER) userId: Long,
        @RequestBody request: CouponIssueRequest
    ) {
        issueCouponUseCase.issueCoupon(IssueCouponCommand(userId, request.couponConfigId))
    }

    @GetMapping
    fun getCoupons(
        @RequestHeader(X_AUTHENTICATED_USER) userId: Long
    ): ApiResponse<List<UserCouponResponse>> =
        ApiResponse(findUserCouponUseCase.findUserCoupon(userId))
}