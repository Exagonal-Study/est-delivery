package com.example.estdelivery.domain.coupon

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import org.junit.jupiter.api.Assertions.*

@DisplayName("쿠폰 수량 도메인은")
class CouponQuantityTest: BehaviorSpec ({

    Given("쿠폰의 수량이 주어지고") {
        val quantity = 100L

        When("쿠폰 수량을 생성하면") {
            val couponQuantity = CouponQuantity(quantity)

            Then("쿠폰 수량이 생성된다") {
                assertAll(
                    { assertNotNull(couponQuantity) },
                    { assertEquals(quantity, couponQuantity.quantity) }
                )
            }
        }

        When("쿠폰 하나를 발급하면") {
            val couponQuantity = CouponQuantity(quantity)
            val minusCouponQuantity = couponQuantity.minus()

            Then("쿠폰 수량이 하나 감소한다") {
                  assertEquals(99, minusCouponQuantity.quantity)
              }
        }
    }

    Given("만약 쿠폰 수량이 음수고") {
        val negativeQuantity = -1L

        When("쿠폰 수량을 생성하면") {
            val exception = assertThrows(IllegalArgumentException::class.java) {
                CouponQuantity(negativeQuantity)
            }

            Then("에러가 발생한다.") {
                assertEquals("쿠폰 수량은 0보다 작을 수 없습니다.", exception.message)
            }
        }
    }
})
