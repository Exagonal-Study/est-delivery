package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.coupon.CouponType
import com.example.estdelivery.domain.member.Member
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ShopOwnerTest : FreeSpec({

    "모든 회원에게 쿠폰을 나눠줄 수 있다." {
        // given
        val 단골_리스트 = RoyalCustomers()
        val 홍길동 = Member(1, "홍길동", CouponBook())
        val 김철수 = Member(2, "김철수", CouponBook())
        단골_리스트.addRoyalCustomers(홍길동, 김철수)

        val 가게_주인 = ShopOwner(Shop(CouponBook(), CouponBook(), CouponBook(), 단골_리스트))
        val 나눠줄_쿠폰 = Coupon.FixDiscountCoupon(1, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_HAND_OUT)

        // when
        가게_주인.handOutCouponToRoyalCustomersInShop(나눠줄_쿠폰)

        // then
        가게_주인.showHandOutCouponInShop().contains(나눠줄_쿠폰) shouldBe true
    }

    "쿠폰을 가게에 게시한다." {
        // given
        val 가게_주인 = ShopOwner(Shop(CouponBook(), CouponBook(), CouponBook(), RoyalCustomers()))
        val 게시할_쿠폰 = Coupon.FixDiscountCoupon(1, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_PUBLISHED)

        // when
        가게_주인.publishCouponInShop(게시할_쿠폰)

        // then
        가게_주인.showPublishedCouponsInShop().contains(게시할_쿠폰) shouldBe true
    }

    "단골 회원을 가게에 추가한다." {
        // given
        val 가게_주인 = ShopOwner(Shop(CouponBook(), CouponBook(), CouponBook(), RoyalCustomers()))
        val 홍길동 = Member(1, "홍길동", CouponBook())
        val 김철수 = Member(2, "김철수", CouponBook())

        // when
        가게_주인.addRoyalCustomersInShop(홍길동, 김철수)

        // then
        가게_주인.showRoyalCustomersInShop().contains(홍길동) shouldBe true
        가게_주인.showRoyalCustomersInShop().contains(김철수) shouldBe true
    }
})
