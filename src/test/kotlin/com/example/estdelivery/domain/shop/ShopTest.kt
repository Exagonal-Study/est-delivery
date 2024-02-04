package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.coupon.CouponType
import com.example.estdelivery.domain.member.Member
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ShopTest : FreeSpec({

    lateinit var 매장: Shop
    val 게시할_쿠폰 = Coupon.FixDiscountCoupon(1, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_PUBLISHED)

    beforeTest {
        val 단골_리스트 = RoyalCustomers()
        val 홍길동 = Member(1, "홍길동", CouponBook())
        val 김철수 = Member(2, "김철수", CouponBook())
        val 이영희 = Member(3, "이영희", CouponBook())
        단골_리스트.addRoyalCustomers(홍길동, 김철수, 이영희)
        매장 = Shop(CouponBook(), CouponBook(), CouponBook(), 단골_리스트)
    }

    "쿠폰을 게시할 수 있다." {
        매장.publishCoupon(게시할_쿠폰)
        매장.showPublishedCoupons().contains(게시할_쿠폰) shouldBe true
    }

    "이미 사용한 쿠폰인지 확인할 수 있다." {
        매장.publishCoupon(게시할_쿠폰)
        매장.useCoupon(게시할_쿠폰)
        매장.alreadyUsedCoupon(게시할_쿠폰) shouldBe true
    }

    "이미 사용한 쿠폰은 재사용 할 수 없다." {
        매장.publishCoupon(게시할_쿠폰)
        매장.useCoupon(게시할_쿠폰)
        shouldThrow<IllegalArgumentException> { 매장.useCoupon(게시할_쿠폰) }
    }

    "게시하지 않는 쿠폰인 경우 사용 할 수 없다." {
        val 게시되지_않은_쿠폰 = Coupon.FixDiscountCoupon(2, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_PUBLISHED)
        shouldThrow<IllegalArgumentException> { 매장.useCoupon(게시되지_않은_쿠폰) }
    }

    "나눠준 쿠폰이 아닌 경우 사용 할 수 없다." {
        val 나눠주지_않은_쿠폰 = Coupon.FixDiscountCoupon(2, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_HAND_OUT)
        shouldThrow<IllegalArgumentException> { 매장.useCoupon(나눠주지_않은_쿠폰) }
    }

    "모든 회원에게 쿠폰을 나눠줄 수 있다." {
        // given
        val 나눠줄_쿠폰_발급 = Coupon.FixDiscountCoupon(1, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_HAND_OUT)

        // when
        매장.handOutCouponToRoyalCustomers(나눠줄_쿠폰_발급)

        // then
        매장.showHandOutCoupon().contains(나눠줄_쿠폰_발급) shouldBe true
        for (royalMember in 매장.showRoyalCustomers()) {
            royalMember.showMyCouponBook().showCoupons().contains(나눠줄_쿠폰_발급) shouldBe true
        }
    }

    "단골 회원을 추가할 수 있다." {
        val 새로운_철수 = Member(14, "새로운 철수", CouponBook())
        매장.addRoyalCustomers(새로운_철수)
        매장.showRoyalCustomers().contains(새로운_철수) shouldBe true
    }
})