package com.example.estdelivery.adapters.out.persistence.coupon.mapper

import com.example.estdelivery.adapters.out.persistence.coupon.entity.CouponEntity
import com.example.estdelivery.domain.coupon.Coupon

object CouponMapper {

    fun fromDomain(coupon: Coupon): CouponEntity {
        return CouponEntity(
            name = coupon.name,
            type = coupon.type,
            discountValue = coupon.discountValue,
            expiryDate = coupon.expiryDate
        )
    }

    fun toDomain(couponEntity: CouponEntity): Coupon {
        return Coupon(
            id = couponEntity.id!!,
            name = couponEntity.name,
            type = couponEntity.type,
            discountValue = couponEntity.discountValue,
            expiryDate = couponEntity.expiryDate
        )
    }
}