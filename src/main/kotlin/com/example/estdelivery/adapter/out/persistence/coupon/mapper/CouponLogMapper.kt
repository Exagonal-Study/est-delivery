package com.example.estdelivery.adapter.out.persistence.coupon.mapper

import com.example.estdelivery.adapter.out.persistence.coupon.entity.CouponLogJpaEntity
import com.example.estdelivery.domain.coupon.CouponLog
import com.example.estdelivery.domain.coupon.CouponNumber
import com.example.estdelivery.domain.coupon.CouponQuantity

object CouponLogMapper {

    fun toEntity(couponLog: CouponLog): CouponLogJpaEntity {
        return CouponLogJpaEntity(
            couponId = couponLog.couponNumber.number,
            userId = couponLog.memberId,
            quantity = couponLog.couponQuantity.quantity
        )
    }

    fun toDomain(couponLogJpaEntity: CouponLogJpaEntity): CouponLog {
        return CouponLog(
            couponId = couponLogJpaEntity.id!!,
            couponNumber = CouponNumber(couponLogJpaEntity.couponId),
            memberId = couponLogJpaEntity.userId,
            couponQuantity = CouponQuantity(couponLogJpaEntity.quantity)
        )
    }
}
