package com.example.estdelivery.application.port.out

import com.example.estdelivery.domain.coupon.CouponLog

interface CouponLogPersistencePort {
    fun saveCouponLog(couponLog: CouponLog): CouponLog

    fun findCouponLogByIdAndMemberId(couponId: String, memberId: Long): CouponLog
}
