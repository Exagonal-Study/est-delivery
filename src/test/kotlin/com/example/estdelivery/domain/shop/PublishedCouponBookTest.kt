package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.fixture.게시할_쿠폰
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PublishedCouponBookTest : FunSpec({

    test("게시된 쿠폰을 추가할 수 있다.") {
        // given
        val publishedCouponBook = PublishedCouponBook()

        // when
        publishedCouponBook.publishCoupon(게시할_쿠폰)

        // then
        publishedCouponBook.showPublishedCoupons().contains(게시할_쿠폰) shouldBe true
    }

    test("이미 추가된 쿠폰은 추가할 수 없다.") {
        // given
        val publishedCouponBook = PublishedCouponBook()
        publishedCouponBook.publishCoupon(게시할_쿠폰)

        // then
        shouldThrow<IllegalArgumentException> { publishedCouponBook.publishCoupon(게시할_쿠폰) }
    }
})
