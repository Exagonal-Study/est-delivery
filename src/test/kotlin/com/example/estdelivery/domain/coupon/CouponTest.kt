package com.example.estdelivery.domain.coupon

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CouponTest : FreeSpec({

    "게시된 쿠폰인지 확인 할 수 있다." {
        val coupon = Coupon("쿠폰", "쿠폰 설명", CouponType.IS_PUBLISHED)
        coupon.isPublished() shouldBe true
    }

    "발급된 쿠폰인지 확인 할 수 있다." {
        val coupon = Coupon("쿠폰", "쿠폰 설명", CouponType.IS_HAND_OUT)
        coupon.isHandOut() shouldBe true
    }
})
