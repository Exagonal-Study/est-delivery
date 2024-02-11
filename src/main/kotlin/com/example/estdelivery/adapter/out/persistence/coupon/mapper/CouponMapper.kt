package com.example.estdelivery.adapter.out.persistence.coupon.mapper

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.adapter.out.persistence.coupon.entity.CouponJpaEntity
import com.example.estdelivery.domain.coupon.CouponQuantity

object CouponMapper {

    fun toEntity(coupon: Coupon): CouponJpaEntity = CouponJpaEntity(
        couponName = coupon.name,
        couponQuantity = coupon.getCouponQuantity(),
        couponType = coupon.type,
        couponEventType = coupon.couponEventType
    )

    fun toDomain(couponJpaEntity: CouponJpaEntity): Coupon = Coupon(
        id = couponJpaEntity.id,
        name = couponJpaEntity.name,
        quantity = CouponQuantity(couponJpaEntity.quantity),
        type = couponJpaEntity.type,
        couponEventType = couponJpaEntity.couponEventType,
        createdDate = couponJpaEntity.createAt
    )
}
