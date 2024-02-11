package com.example.estdelivery.domain.fixture

import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.domain.member.UnusedCouponBook

val 일건창 = Member(1L, "일건창", UnusedCouponBook(CouponBook(listOf())))
val 이건창 = Member(2L, "이건창", UnusedCouponBook(CouponBook(listOf())))