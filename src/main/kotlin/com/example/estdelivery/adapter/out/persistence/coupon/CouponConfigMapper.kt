package com.example.estdelivery.adapter.out.persistence.coupon

import com.example.estdelivery.application.domain.model.CouponConfig


object CouponConfigMapper {
    fun toCouponConfig(couponConfigEntity: CouponConfigEntity): CouponConfig =
        CouponConfig(
            id = couponConfigEntity.id!!,
            type = couponConfigEntity.type,
            json = couponConfigEntity.json
        )
}
