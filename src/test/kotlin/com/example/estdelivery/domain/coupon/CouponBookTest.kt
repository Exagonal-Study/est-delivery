package com.example.estdelivery.domain.coupon

import com.example.estdelivery.domain.fixture.게시된_고정_할인_쿠폰
import com.example.estdelivery.domain.fixture.나눠준_비율_할인_쿠폰
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CouponBookTest : FreeSpec({

    lateinit var couponBook: CouponBook

    beforeTest {
        couponBook = CouponBook()
    }

    "쿠폰을 추가할 수 있다." {
        couponBook.addCoupon(나눠준_비율_할인_쿠폰)
        couponBook.addCoupon(게시된_고정_할인_쿠폰)
    }

    "쿠폰을 삭제할 수 있다." {
        couponBook.addCoupon(나눠준_비율_할인_쿠폰)
        couponBook.deleteCoupon(나눠준_비율_할인_쿠폰)
    }

    "쿠폰을 중복으로 추가할 수 없다." {
        val coupon = 나눠준_비율_할인_쿠폰
        couponBook.addCoupon(coupon)

        shouldThrow<IllegalArgumentException> {
            couponBook.addCoupon(나눠준_비율_할인_쿠폰)
        }.message shouldBe "이미 존재하는 쿠폰입니다."
    }

    "존재하지 않는 쿠폰을 삭제할 수 없다." {
        val 존재하지_않는_쿠폰 = Coupon.RateDiscountCoupon(10, "10% 할인 쿠폰", "10% 할인 쿠폰 설명", CouponType.IS_HAND_OUT, 100L)
        shouldThrow<IllegalArgumentException> {
            couponBook.deleteCoupon(존재하지_않는_쿠폰)
        }.message shouldBe "존재하지 않는 쿠폰입니다."
    }
})
