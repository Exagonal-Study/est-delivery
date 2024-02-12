package com.example.estdelivery.usecase

import CouponTestFixture
import com.example.estdelivery.application.domain.service.FindUserCouponService
import com.example.estdelivery.application.port.out.FindUserCouponPort
import io.kotest.core.spec.style.FreeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class FindUserCouponUseCaseTest : FreeSpec({
    val findUserCouponPort = mockk<FindUserCouponPort>()

    val findUserCouponService = FindUserCouponService(findUserCouponPort)

    "findUserCoupons" - {
        "주어진 사용자의 쿠폰 정보를 조회한다" - {
            val userId = 1L
            every { findUserCouponPort.findByUserId(any()) } returns CouponTestFixture.coupons()

            findUserCouponService.findUserCoupons(userId)

            verify { findUserCouponPort.findByUserId(userId) }
        }
    }
})