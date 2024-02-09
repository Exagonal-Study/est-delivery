package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.fixture.게시된_고정_할인_쿠폰
import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.domain.member.UnUsedCouponBook
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class RoyalCustomersTest : FreeSpec({

    "모든 회원에게 쿠폰을 나눠줄 수 있다." {
        // given
        val 단골_리스트 = RoyalCustomers()
        val 홍길동 = Member(1, "홍길동", UnUsedCouponBook())
        val 김철수 = Member(2, "김철수", UnUsedCouponBook())
        val 이영희 = Member(3, "이영희", UnUsedCouponBook())
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
        val 홍길동 = Member(1, "홍길동", UnUsedCouponBook())
        단골_리스트.addRoyalCustomers(홍길동)

        // when
        shouldThrow<IllegalArgumentException> { 단골_리스트.addRoyalCustomers(홍길동) }
    }
})
