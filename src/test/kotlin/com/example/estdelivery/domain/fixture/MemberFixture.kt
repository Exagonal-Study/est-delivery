package com.example.estdelivery.domain.fixture

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.domain.member.UnusedCouponBook

fun 일건창() = Member(1L, "일건창", UnusedCouponBook(CouponBook(listOf())))
fun 이건창() = Member(2L, "이건창", UnusedCouponBook(CouponBook(listOf())))
fun 나눠준_쿠폰을_가진_삼건창(coupon: Coupon) = Member(3L, "이건창", UnusedCouponBook(CouponBook(listOf(coupon))))
fun 게시된_쿠폰을_가진_사건창(coupon: Coupon) = Member(4L, "이건창", UnusedCouponBook(CouponBook(listOf(coupon))))
