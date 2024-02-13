package com.example.estdelivery.adapter.out.persistence.coupon.repository

import com.example.estdelivery.adapter.out.persistence.coupon.entity.CouponLogJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CouponLogJpaRepository : JpaRepository<CouponLogJpaEntity, Long> {
    fun findByCouponIdAndUserId(couponId: String, userId: Long): Optional<CouponLogJpaEntity>
}
