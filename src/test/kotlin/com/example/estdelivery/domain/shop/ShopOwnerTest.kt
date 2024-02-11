package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.fixture.게시할_쿠폰
import com.example.estdelivery.domain.fixture.나눠줄_쿠폰
import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.domain.member.UnusedCouponBook
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ShopOwnerTest : FreeSpec({
    val 프리퍼 = Shop(PublishedCouponBook(), HandOutCouponBook(), UsedCouponBook(), RoyalCustomers(), "프리퍼")

    "모든 회원에게 쿠폰을 나눠줄 수 있다." {
        // given
        val 단골_리스트 = RoyalCustomers()
        val 홍길동 = Member(1, "홍길동", UnusedCouponBook())
        val 김철수 = Member(2, "김철수", UnusedCouponBook())
        단골_리스트.addRoyalCustomers(홍길동, 김철수)

        val 프리퍼_가게_사장님 = ShopOwner(Shop(PublishedCouponBook(), HandOutCouponBook(), UsedCouponBook(), 단골_리스트, "프리퍼"))

        // when
        프리퍼_가게_사장님.handOutCouponToRoyalCustomersInShop(나눠줄_쿠폰)

        // then
        프리퍼_가게_사장님.showHandOutCouponInShop().contains(나눠줄_쿠폰) shouldBe true
    }

    "쿠폰을 가게에 게시한다." {
        // given
        val 가게_주인 = ShopOwner(프리퍼)

        // when
        가게_주인.publishCouponInShop(게시할_쿠폰)

        // then
        가게_주인.showPublishedCouponsInShop().contains(게시할_쿠폰) shouldBe true
    }

    "단골 회원을 가게에 추가한다." {
        // given
        val 가게_주인 = ShopOwner(프리퍼)
        val 홍길동 = Member(1, "홍길동", UnusedCouponBook())
        val 김철수 = Member(2, "김철수", UnusedCouponBook())

        // when
        가게_주인.addRoyalCustomersInShop(홍길동, 김철수)

        // then
        가게_주인.showRoyalCustomersInShop().contains(홍길동) shouldBe true
        가게_주인.showRoyalCustomersInShop().contains(김철수) shouldBe true
    }
})
