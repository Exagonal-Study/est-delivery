package com.example.estdelivery.domain.generator

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertNotNull

@DisplayName("쿠폰 아이디 생성기는")
class CouponIdGeneratorTest : BehaviorSpec({

    Given("쿠폰 발급 아이디 요청이 들어오고") {
        When("쿠폰 아이디 발급기가 요청되면") {
            val couponNumber = CouponIdGenerator.generate()

            Then("13글자의 쿠폰 아이디가 생성된다") {
                assertAll(
                    { assertNotNull(couponNumber) },
                    { couponNumber.number.length shouldBe 13 }
                )
            }
        }
    }
})
