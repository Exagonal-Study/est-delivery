package com.example.estdelivery.usecase

import CouponConfigTestFixture
import CouponTestFixture
import com.example.estdelivery.application.domain.service.IssueCouponService
import com.example.estdelivery.application.port.`in`.IssueCouponCommand
import com.example.estdelivery.application.port.out.CreateCouponPort
import com.example.estdelivery.application.port.out.FindCouponConfigPort
import com.example.estdelivery.application.port.out.FindUserCouponPort
import com.example.estdelivery.application.port.out.IssueUserCouponPort
import com.example.estdelivery.common.exception.CommonException
import com.example.estdelivery.common.exception.ErrorCode
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.assertThrows

class IssueCouponUseCaseTest : FreeSpec({
    val findCouponConfigPort = mockk<FindCouponConfigPort>()
    val createCouponPort = mockk<CreateCouponPort>()
    val issueUserCouponPort = mockk<IssueUserCouponPort>()
    val findUserCouponPort = mockk<FindUserCouponPort>()

    val issueCouponService =
        IssueCouponService(findCouponConfigPort, createCouponPort, issueUserCouponPort, findUserCouponPort)

    "issueCoupon" - {
        "사용자에게 쿠폰을 발급한다" - {
            val userId = 1L
            val couponConfigId = 1L
            val issueCouponCommand = IssueCouponCommand(userId, couponConfigId)

            "오늘 동일한 쿠폰을 발급받은 경우" - {
                "DUPLICATED_COUPON Exception 처리한다" - {
                    every { findCouponConfigPort.findCouponConfig(any()) } returns CouponConfigTestFixture.couponConfig()
                    every { findUserCouponPort.existsIssuedUserCoupon(any(), any()) } returns true
                    every { createCouponPort.createCoupon(any()) } returns CouponTestFixture.coupon()
                    every { issueUserCouponPort.issueUserCoupon(any(), any()) } returns mockk()

                    val exception = assertThrows<CommonException> {
                        issueCouponService.issueCoupon(issueCouponCommand)
                    }

                    exception.errorCode shouldBe ErrorCode.DUPLICATED_COUPON
                    verify { findCouponConfigPort.findCouponConfig(couponConfigId) }
                    verify { findUserCouponPort.existsIssuedUserCoupon(userId, any()) }
                    verify(exactly = 0) { createCouponPort.createCoupon(any()) }
                    verify(exactly = 0) { issueUserCouponPort.issueUserCoupon(userId, any()) }
                }
            }

            "오늘 동일한 쿠폰을 발급받지 않은 경우" - {
                "쿠폰 발급에 성공한다" - {
                    every { findCouponConfigPort.findCouponConfig(any()) } returns CouponConfigTestFixture.couponConfig()
                    every { findUserCouponPort.existsIssuedUserCoupon(any(), any()) } returns false
                    every { createCouponPort.createCoupon(any()) } returns CouponTestFixture.coupon()
                    every { issueUserCouponPort.issueUserCoupon(any(), any()) } returns mockk()

                    issueCouponService.issueCoupon(issueCouponCommand)

                    verify { findCouponConfigPort.findCouponConfig(couponConfigId) }
                    verify { findUserCouponPort.existsIssuedUserCoupon(userId, any()) }
                    verify { createCouponPort.createCoupon(any()) }
                    verify { issueUserCouponPort.issueUserCoupon(userId, any()) }
                }
            }
        }
    }
})