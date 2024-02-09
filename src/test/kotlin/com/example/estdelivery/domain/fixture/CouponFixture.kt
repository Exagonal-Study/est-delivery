package com.example.estdelivery.domain.fixture

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponType

val 나눠준_비율_할인_쿠폰 = Coupon.RateDiscountCoupon(1, 10, "10% 할인 쿠폰", "10% 할인 쿠폰 설명", CouponType.IS_HAND_OUT)
val 게시된_고정_할인_쿠폰 = Coupon.FixDiscountCoupon(2, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_PUBLISHED)
val 게시할_쿠폰 = Coupon.FixDiscountCoupon(1, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_PUBLISHED)
val 나눠줄_쿠폰 = Coupon.FixDiscountCoupon(1, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_HAND_OUT)
val 게시되지_않은_쿠폰 = Coupon.FixDiscountCoupon(2, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_PUBLISHED)
val 나눠주지_않은_쿠폰 = Coupon.FixDiscountCoupon(2, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_HAND_OUT)

