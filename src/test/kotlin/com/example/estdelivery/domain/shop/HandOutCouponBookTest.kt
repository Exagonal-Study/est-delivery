package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.fixture.게시할_쿠폰
import com.example.estdelivery.domain.fixture.나눠줄_쿠폰
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class HandOutCouponBookTest : FreeSpec({

    "나눠줄 쿠폰을 담을 수 있다." {
        // given
        val handOutCouponBook = HandOutCouponBook()

        // when
        handOutCouponBook.addHandOutCoupon(나눠줄_쿠폰)

        // then
        handOutCouponBook.showHandOutCoupon().contains(나눠줄_쿠폰) shouldBe true
    }

    "이미 추가된 쿠폰은 담기지 않는다." {
        // given
        val handOutCouponBook = HandOutCouponBook()

        // when
        handOutCouponBook.addHandOutCoupon(나눠줄_쿠폰)
        handOutCouponBook.addHandOutCoupon(나눠줄_쿠폰)
        // then
        handOutCouponBook.showHandOutCoupon().size shouldBe 1
    }

    "나눠줄 수 없는 쿠폰은 담을 수 없다." {
        // given
        val handOutCouponBook = HandOutCouponBook()

        // when & then
        shouldThrow<IllegalArgumentException> { handOutCouponBook.addHandOutCoupon(게시할_쿠폰) }
            .message shouldBe "나눠줄 수 없는 쿠폰입니다."
    }
})
