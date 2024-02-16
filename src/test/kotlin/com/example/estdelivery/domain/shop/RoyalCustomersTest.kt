package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.fixture.게시된_고정_할인_쿠폰
import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.domain.member.UnusedCouponBook
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class RoyalCustomersTest : FreeSpec({

    "모든 회원에게 쿠폰을 나눠줄 수 있다." {
        // given
        val 단골_리스트 = RoyalCustomers()
        val 홍길동 = Member(1, "홍길동", UnusedCouponBook())
        val 김철수 = Member(2, "김철수", UnusedCouponBook())
        val 이영희 = Member(3, "이영희", UnusedCouponBook())
        단골_리스트.addRoyalCustomers(홍길동, 김철수, 이영희)

        // when
        단골_리스트.handOutCoupon(게시된_고정_할인_쿠폰)

        // then
        for (royalMember in 단골_리스트.showRoyalCustomers()) {
            royalMember.showMyCouponBook().contains(게시된_고정_할인_쿠폰) shouldBe true
        }
    }

    "쿠폰을 가진 사용자에게는 쿠폰을 나눠주지 않는다." {
        // given
        val 단골_리스트 = RoyalCustomers()
        val 홍길동 = Member(1, "홍길동", UnusedCouponBook(CouponBook(listOf(게시된_고정_할인_쿠폰))))
        val 김철수 = Member(2, "김철수", UnusedCouponBook())
        val 이영희 = Member(3, "이영희", UnusedCouponBook())
        단골_리스트.addRoyalCustomers(홍길동, 김철수, 이영희)

        // when
        단골_리스트.handOutCoupon(게시된_고정_할인_쿠폰)

        // then
        for (royalMember in 단골_리스트.showRoyalCustomers()) {
            royalMember.showMyCouponBook().contains(게시된_고정_할인_쿠폰) shouldBe true
        }
    }

    "이미 추가된 회원은 추가할 수 없다." {
        // given
        val 단골_리스트 = RoyalCustomers()
        val 홍길동 = Member(1, "홍길동", UnusedCouponBook())
        단골_리스트.addRoyalCustomers(홍길동)

        // when
        shouldThrow<IllegalArgumentException> { 단골_리스트.addRoyalCustomers(홍길동) }
            .message shouldBe "이미 등록된 회원입니다."
    }
})
