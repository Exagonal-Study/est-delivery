package com.example.estdelivery.domain.coupon

import com.example.estdelivery.domain.generator.CouponIdGenerator
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import org.junit.jupiter.api.Assertions.*

@DisplayName("쿠폰 로그 도메인은")
class CouponLogTest: BehaviorSpec ({
    Given("회원 아이디, 쿠폰 아이디, 쿠폰 번호, 쿠폰 수량, 생성일이 주어지고") {
        val memberId = 1L
        val couponId = 1L
        val couponNumber = CouponIdGenerator.generate()
        val couponQuantity = CouponQuantity(1)

        When("쿠폰 로그를 생성하면") {
            val couponLog = CouponLog(memberId = memberId, couponId = couponId, couponNumber = couponNumber, couponQuantity = couponQuantity)

            Then("쿠폰 로그가 생성된다") {
                assertAll(
                    { assertNotNull(couponLog) },
                    { assertEquals(memberId, couponLog.memberId) },
                    { assertEquals(couponId, couponLog.couponId) },
                    { assertEquals(couponNumber, couponLog.couponNumber) },
                    { assertEquals(couponQuantity, couponLog.couponQuantity) }
                )
            }
        }
    }

})
