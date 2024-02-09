package com.example.estdelivery.domain.member

import com.example.estdelivery.domain.fixture.게시된_고정_할인_쿠폰
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class UnusedCouponBookTest : FreeSpec({

    "쿠폰을 추가할 수 있다." {
        // given
        val unusedCouponBook = UnusedCouponBook()

        // when
        unusedCouponBook.addUnusedCoupon(게시된_고정_할인_쿠폰)

        // then
        unusedCouponBook.showUnusedCoupons().contains(게시된_고정_할인_쿠폰) shouldBe true
    }

    "쿠폰을 사용할 수 있다." {
        // given
        val unusedCouponBook = UnusedCouponBook()
        unusedCouponBook.addUnusedCoupon(게시된_고정_할인_쿠폰)

        // when
        unusedCouponBook.removeUsedCoupon(게시된_고정_할인_쿠폰)

        // then
        unusedCouponBook.showUnusedCoupons().contains(게시된_고정_할인_쿠폰) shouldBe false
    }

    "이미 추가된 쿠폰은 추가할 수 없다." {
        // given
        val unusedCouponBook = UnusedCouponBook()
        unusedCouponBook.addUnusedCoupon(게시된_고정_할인_쿠폰)

        // then
        shouldThrow<IllegalArgumentException> { unusedCouponBook.addUnusedCoupon(게시된_고정_할인_쿠폰) }
    }
})
