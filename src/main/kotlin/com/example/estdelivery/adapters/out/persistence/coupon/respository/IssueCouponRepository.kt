package com.example.estdelivery.adapters.out.persistence.coupon.respository

import com.example.estdelivery.adapters.out.persistence.coupon.entity.IssuedCouponEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.util.Optional

interface IssueCouponRepository : JpaRepository<IssuedCouponEntity, Long> {
    fun findByMemberIdAndCouponIdAndCreatedAtBetween(
        memberId: Long,
        couponId: Long,
        startDate: LocalDateTime,
        endDate: LocalDateTime,
    ): Optional<IssuedCouponEntity>
}