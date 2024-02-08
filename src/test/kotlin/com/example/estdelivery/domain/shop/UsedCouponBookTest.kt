package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.coupon.CouponType
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class UsedCouponBookTest : FreeSpec({
    "이미 사용한 쿠폰은 재사용 할 수 없다." {
        // given
        val usedCouponBook = UsedCouponBook()
        val shopCouponBook = CouponBook()
        val coupon = Coupon.FixDiscountCoupon(1, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_PUBLISHED)

        // when
        shopCouponBook.addCoupon(coupon)
        usedCouponBook.useCoupon(coupon, shopCouponBook)

        // then
        shouldThrow<IllegalArgumentException> { usedCouponBook.useCoupon(coupon, shopCouponBook) }
    }

    "게시하지 않는 쿠폰인 경우 사용 할 수 없다." {
        // given
        val usedCouponBook = UsedCouponBook()
        val shopCouponBook = CouponBook()
        val notPublishedCoupon = Coupon.FixDiscountCoupon(2, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_PUBLISHED)

        // then
        shouldThrow<IllegalArgumentException> { usedCouponBook.useCoupon(notPublishedCoupon, shopCouponBook) }
    }
})
