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
