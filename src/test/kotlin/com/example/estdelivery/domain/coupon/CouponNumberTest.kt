package com.example.estdelivery.domain.coupon

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import org.junit.jupiter.api.Assertions.*

@DisplayName("쿠폰 번호 도메인은")
class CouponNumberTest: BehaviorSpec ({

    Given("쿠폰 번호가 주어지고") {
        val number= "1N2N3nfd453"

        When("쿠폰 번호를 생성하면") {
            val couponNumber = CouponNumber(number)

            Then("쿠폰 번호가 생성된다") {
                assertAll(
                    { assertNotNull(couponNumber) },
                    { assertEquals(number, couponNumber.number) }
                )
            }
        }
    }

    Given("만약 쿠폰번호가 빈값이라면") {
        val emptyNumber = ""

        When("쿠폰 번호를 생성하면") {
            val exception = assertThrows(IllegalArgumentException::class.java) {
                CouponNumber(emptyNumber)
            }

            Then("에러가 발생한다.") {
                assertEquals("쿠폰 번호가 입력되지 않았습니다.", exception.message)
            }
        }
    }
})
