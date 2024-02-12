package com.example.estdelivery.adapters.out.persistence.coupon.respository

import com.example.estdelivery.adapters.out.persistence.coupon.entity.CouponEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository : JpaRepository<CouponEntity, Long> {
}