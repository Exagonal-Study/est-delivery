package com.example.estdelivery.domain.coupon

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CouponTest : FreeSpec({
    val 나눠준_비율_할인_쿠폰 = Coupon.RateDiscountCoupon(1, 10, "10% 할인 쿠폰", "10% 할인 쿠폰 설명", CouponType.IS_HAND_OUT)
    val 게시된_고정_할인_쿠폰 = Coupon.FixDiscountCoupon(2, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_PUBLISHED)

    "게시된 쿠폰인지 확인 할 수 있다." {
        게시된_고정_할인_쿠폰.isPublished() shouldBe true
    }

    "발급된 쿠폰인지 확인 할 수 있다." {
        나눠준_비율_할인_쿠폰.isHandOut() shouldBe true
    }

    "할인율 쿠폰을 생성할 수 있다." {
        나눠준_비율_할인_쿠폰.discountRate shouldBe 10
    }

    "고정 할인 쿠폰을 생성할 수 있다." {
        게시된_고정_할인_쿠폰.discountAmount shouldBe 1000
    }
})
