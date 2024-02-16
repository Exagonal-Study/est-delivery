package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.fixture.게시되지_않은_쿠폰
import com.example.estdelivery.domain.fixture.게시할_쿠폰
import com.example.estdelivery.domain.fixture.나눠주지_않은_쿠폰
import com.example.estdelivery.domain.fixture.나눠줄_쿠폰
import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.domain.member.UnusedCouponBook
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

private const val s = "프리퍼"

class ShopTest : FreeSpec({

    lateinit var 프리퍼: Shop

    beforeTest {
        val 단골_리스트 = RoyalCustomers()
        val 홍길동 = Member(1, "홍길동", UnusedCouponBook())
        val 김철수 = Member(2, "김철수", UnusedCouponBook())
        val 이영희 = Member(3, "이영희", UnusedCouponBook())
        단골_리스트.addRoyalCustomers(홍길동, 김철수, 이영희)
        프리퍼 = Shop(PublishedCouponBook(), HandOutCouponBook(), UsedCouponBook(), 단골_리스트, "프리퍼")
    }

    "쿠폰을 게시할 수 있다." {
        프리퍼.publishCoupon(게시할_쿠폰)
        프리퍼.showPublishedCoupons().contains(게시할_쿠폰) shouldBe true
    }

    "이미 사용한 쿠폰은 재사용 할 수 없다." {
        프리퍼.publishCoupon(게시할_쿠폰)
        프리퍼.receiveCoupon(게시할_쿠폰)
        shouldThrow<IllegalArgumentException> { 프리퍼.receiveCoupon(게시할_쿠폰) }
    }

    "게시하지 않는 쿠폰인 경우 사용 할 수 없다." {
        shouldThrow<IllegalArgumentException> { 프리퍼.receiveCoupon(게시되지_않은_쿠폰) }
    }

    "나눠준 쿠폰이 아닌 경우 사용 할 수 없다." {
        shouldThrow<IllegalArgumentException> { 프리퍼.receiveCoupon(나눠주지_않은_쿠폰) }
    }

    "모든 회원에게 쿠폰을 나눠줄 수 있다." {
        // when
        프리퍼.handOutCouponToRoyalCustomers(나눠줄_쿠폰)

        // then
        프리퍼.showHandOutCoupon().contains(나눠줄_쿠폰) shouldBe true
        for (royalMember in 프리퍼.showRoyalCustomers()) {
            royalMember.showMyCouponBook().contains(나눠줄_쿠폰) shouldBe true
        }
    }

    "단골 회원을 추가할 수 있다." {
        val 새로운_철수 = Member(14, "새로운 철수", UnusedCouponBook())
        프리퍼.addRoyalCustomers(새로운_철수)
        프리퍼.showRoyalCustomers().contains(새로운_철수) shouldBe true
    }
})