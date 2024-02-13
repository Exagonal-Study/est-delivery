package com.example.estdelivery.usecase

import com.example.estdelivery.application.dto.coupon.GenerateCouponCommand
import com.example.estdelivery.application.dto.coupon.IssuedCouponCommand
import com.example.estdelivery.application.port.out.CouponLogPersistencePort
import com.example.estdelivery.application.port.out.CouponPersistencePort
import com.example.estdelivery.application.port.out.MemberPersistencePort
import com.example.estdelivery.application.service.CouponService
import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponLog
import com.example.estdelivery.domain.coupon.CouponNumber
import com.example.estdelivery.domain.coupon.CouponQuantity
import com.example.estdelivery.domain.coupon.types.CouponEventType
import com.example.estdelivery.domain.coupon.types.CouponType
import com.example.estdelivery.domain.member.Member
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.assertAll
import java.time.LocalDateTime

@DisplayName("쿠폰 유스케이스는")
class CouponWriteUsecaseTest : BehaviorSpec({

    lateinit var memberPersistencePort: MemberPersistencePort
    lateinit var couponLogPersistencePort: CouponLogPersistencePort
    lateinit var couponPersistencePort: CouponPersistencePort
    lateinit var couponService: CouponService

    beforeTest {
        memberPersistencePort = mockk()
        couponLogPersistencePort = mockk()
        couponPersistencePort = mockk()

        couponService = withContext(Dispatchers.IO) {
            CouponService(memberPersistencePort, couponPersistencePort, couponLogPersistencePort)
        }
    }

    Given("쿠폰이름 타입 이벤트 타입 수량이주어지고") {
        val couponName = "EST 테스트 쿠폰"
        val couponType = CouponType.FIXED
        val couponEventType = CouponEventType.EVENT
        val quantity = 100L

        val requestCommand = GenerateCouponCommand(couponName, couponType, couponEventType, quantity)

        When("쿠폰 발급을 요청하면") {
            every { couponPersistencePort.saveCoupon(any()) } returns requestCommand.toCouponDomain()
            val generateCoupon = couponService.generateCoupon(requestCommand)

            Then("쿠폰이 발급된다") {
                assertAll(
                    { generateCoupon.couponName shouldBe couponName },
                    { generateCoupon.couponType shouldBe couponType },
                    { generateCoupon.couponEventType shouldBe couponEventType },
                    { generateCoupon.quantity shouldBe quantity }
                )
            }
        }
    }

    Given("멤버 아이디와 발급 대상의 쿠폰아이디가 주어지고") {
        val memberId = 1L
        val couponId = 1L

        val mockMember = Member(1L, "test@test.com", LocalDateTime.now())
        val mockCoupon = Coupon(1L, "EST 테스트 쿠폰", CouponQuantity(100L), CouponType.FIXED, CouponEventType.EVENT, LocalDateTime.now())
        val mockCouponLog = CouponLog(1L,1L, CouponNumber("12df34gf3f"), CouponQuantity(1), LocalDateTime.now())

        val issuedCouponCommand = IssuedCouponCommand(couponId)
        When("쿠폰 발급을 요청하면") {
            every { memberPersistencePort.findMemberById(any()) } returns mockMember
            every { couponPersistencePort.findCouponById(any()) } returns mockCoupon
            every { couponPersistencePort.updateCouponQuantity(any()) } returns Unit
            every { couponLogPersistencePort.saveCouponLog(any()) } returns mockCouponLog

            val issuedCoupon = couponService.issuedCoupon(memberId, issuedCouponCommand)

            Then("쿠폰이 발급된다") {
                assertAll(
                    { issuedCoupon.couponName shouldBe "EST 테스트 쿠폰" },
                    { issuedCoupon.couponType shouldBe CouponType.FIXED },
                    { issuedCoupon.couponEventType shouldBe CouponEventType.EVENT },
                    { issuedCoupon.quantity shouldBe 1L }
                )
            }
        }

    }

})
