package com.example.estdelivery.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface UserCouponRepository : JpaRepository<UserCouponEntity, Long> {
    fun existsByUserIdAndCouponConfigIdAndCreatedAtBetween(
        userId: Long,
        couponConfigId: Long,
        from: LocalDateTime,
        to: LocalDateTime
    ): Boolean

    fun findByUserId(userId: Long): List<UserCouponEntity>
}
