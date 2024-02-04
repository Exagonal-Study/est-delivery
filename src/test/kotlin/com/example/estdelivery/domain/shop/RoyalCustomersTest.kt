package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.coupon.CouponType
import com.example.estdelivery.domain.member.Member
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RoyalCustomersTest : FreeSpec({

    "모든 회원에게 쿠폰을 나눠줄 수 있다." {
        // given
        val 단골_리스트 = RoyalCustomers()
        val 홍길동 = Member(1, "홍길동", CouponBook())
        val 김철수 = Member(2, "김철수", CouponBook())
        val 이영희 = Member(3, "이영희", CouponBook())
        단골_리스트.addRoyalCustomers(홍길동, 김철수, 이영희)
        val coupon = Coupon.FixDiscountCoupon(1, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_HAND_OUT)

        // when
        단골_리스트.handOutCoupon(coupon)

        // then
        for (royalMember in 단골_리스트.showRoyalCustomers()) {
            royalMember.showMyCouponBook().showCoupons().contains(coupon) shouldBe true
        }
    }
})
