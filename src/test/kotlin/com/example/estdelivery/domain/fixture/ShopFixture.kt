package com.example.estdelivery.domain.fixture

import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.shop.*

val 프리퍼 = Shop(
    PublishedCouponBook(CouponBook(listOf())),
    HandOutCouponBook(CouponBook(listOf())),
    UsedCouponBook(CouponBook(listOf())),
    RoyalCustomers(listOf()),
    "프리퍼",
    1L
)