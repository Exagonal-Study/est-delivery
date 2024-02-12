package com.example.estdelivery.adapters.out.persistence.coupon.respository

import com.example.estdelivery.adapters.out.persistence.coupon.entity.CouponEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface CouponRepository : JpaRepository<CouponEntity, Long> {
    fun findByExpiryDateAfter(currentDate: LocalDate): List<CouponEntity>
}