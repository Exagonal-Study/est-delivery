package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.fixture.게시할_쿠폰
import com.example.estdelivery.domain.fixture.나눠줄_쿠폰
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PublishedCouponBookTest : FreeSpec({

    "게시된 쿠폰을 추가할 수 있다." {
        // given
        val publishedCouponBook = PublishedCouponBook()

        // when
        publishedCouponBook.publishCoupon(게시할_쿠폰)

        // then
        publishedCouponBook.showPublishedCoupons().contains(게시할_쿠폰) shouldBe true
    }

    "이미 추가된 쿠폰은 추가할 수 없다." {
        // given
        val publishedCouponBook = PublishedCouponBook()
        publishedCouponBook.publishCoupon(게시할_쿠폰)

        // then
        shouldThrow<IllegalArgumentException> { publishedCouponBook.publishCoupon(게시할_쿠폰) }
            .message shouldBe "이미 게시한 쿠폰입니다."
    }

    "게시할 수 없는 쿠폰은 추가할 수 없다." {
        // given
        val publishedCouponBook = PublishedCouponBook()

        // when & then
        shouldThrow<IllegalArgumentException> { publishedCouponBook.publishCoupon(나눠줄_쿠폰) }
            .message shouldBe "게시할 수 없는 쿠폰입니다."
    }
})
