package com.example.estdelivery.domain.coupon

import com.example.estdelivery.domain.coupon.types.CouponEventType
import com.example.estdelivery.domain.coupon.types.CouponType
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertAll

@DisplayName("쿠폰 도메인은")
class CouponTest : BehaviorSpec({

    Given("이름, 수량, 타입, 이벤트 타입이 주어지고") {
        val name = "EST TEST 쿠폰"
        val quantity = CouponQuantity(100)
        val type = CouponType.FIXED
        val couponEventType = CouponEventType.EVENT

        When("쿠폰을 생성하면") {
            val coupon = Coupon(name = name, quantity = quantity, type = type, couponEventType = couponEventType)

            Then("쿠폰이 생성된다") {
                assertAll(
                    { coupon.name shouldBe name },
                    { coupon.quantity shouldBe quantity },
                    { coupon.type shouldBe type },
                    { coupon.couponEventType shouldBe couponEventType }
                )
            }
        }
    }

    Given("만약 쿠폰의 이름이 80자가 넘거나 빈값이 주어지고") {
        val emptyName = ""
        val longName = "2024년 환상적인 신년맞이: 전자제품부터 패션까지 전 범위 대상, 최대 70% 할인 혜택과 추가 적립금 제공하는 초대형 럭셔리 쇼핑 축제 한정판 쿠폰"

        When("쿠폰을 생성하면") {
            val emptyNameException = shouldThrow<IllegalArgumentException> {
                Coupon(name = emptyName, quantity = CouponQuantity(100), type = CouponType.FIXED, couponEventType = CouponEventType.EVENT)
            }

            println(longName.length)

            val longNameException = shouldThrow<IllegalArgumentException> {
                Coupon(name = longName, quantity = CouponQuantity(100), type = CouponType.FIXED, couponEventType = CouponEventType.EVENT)
            }

            Then("에러가 발생한다.") {
                assertAll(
                    { emptyNameException.message shouldBe "쿠폰 이름이 입력되지 않았습니다." },
                    { longNameException.message shouldBe "쿠폰 이름은 80자를 넘을 수 없습니다." }
                )
            }
        }
    }

})
