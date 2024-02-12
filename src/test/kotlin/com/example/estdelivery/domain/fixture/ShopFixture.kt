package com.example.estdelivery.domain.fixture

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.shop.*

const val 가게_이름 = "프리퍼"
val 새로_창업해서_아무것도_없는_프리퍼 = Shop(
    PublishedCouponBook(CouponBook(listOf())),
    HandOutCouponBook(CouponBook(listOf())),
    UsedCouponBook(CouponBook(listOf())),
    RoyalCustomers(listOf()),
    가게_이름,
    1L
)
val 단골_명단 = listOf(나눠준_쿠폰을_가진_삼건창, 일건창, 이건창)
val 단골이_있는_멋진_프리퍼 = Shop(
    PublishedCouponBook(CouponBook(listOf())),
    HandOutCouponBook(CouponBook(listOf())),
    UsedCouponBook(CouponBook(listOf())),
    RoyalCustomers(단골_명단),
    가게_이름,
    2L
)
val 나눠준_쿠폰이_있는_프리퍼 = Shop(
    PublishedCouponBook(CouponBook(listOf())),
    HandOutCouponBook(CouponBook(listOf(나눠준_비율_할인_쿠폰))),
    UsedCouponBook(CouponBook(listOf())),
    RoyalCustomers(단골_명단),
    가게_이름,
    3L
)
val 게시된_쿠폰이_있는_프리퍼 = Shop(
    PublishedCouponBook(CouponBook(listOf(게시된_고정_할인_쿠폰))),
    HandOutCouponBook(CouponBook(listOf())),
    UsedCouponBook(CouponBook(listOf())),
    RoyalCustomers(단골_명단),
    가게_이름,
    3L
)

fun 게시된_쿠폰이_있는_프리퍼(vararg coupon: Coupon): Shop {
    return Shop(
        PublishedCouponBook(CouponBook(listOf(*coupon))),
        HandOutCouponBook(CouponBook(listOf())),
        UsedCouponBook(CouponBook(listOf())),
        RoyalCustomers(listOf()),
        "프리퍼",
        1L
    )
}

