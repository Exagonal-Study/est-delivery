package com.example.estdelivery.domain.fixture

import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.shop.*

const val 가게_이름 = "프리퍼"
val 프리퍼 = Shop(
    PublishedCouponBook(CouponBook(listOf())),
    HandOutCouponBook(CouponBook(listOf())),
    UsedCouponBook(CouponBook(listOf())),
    RoyalCustomers(listOf()),
    가게_이름,
    1L
)
val 단골_명단 = listOf(나눠준_쿠폰을_가진_삼건창, 일건창, 이건창)
val 단골이_있는_프리퍼 = Shop(
    PublishedCouponBook(CouponBook(listOf())),
    HandOutCouponBook(CouponBook(listOf())),
    UsedCouponBook(CouponBook(listOf())),
    RoyalCustomers(단골_명단),
    가게_이름,
    2L
)