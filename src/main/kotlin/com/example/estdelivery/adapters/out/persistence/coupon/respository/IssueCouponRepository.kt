package com.example.estdelivery.adapters.out.persistence.coupon.respository

import com.example.estdelivery.adapters.out.persistence.coupon.entity.IssuedCouponEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IssueCouponRepository : JpaRepository<IssuedCouponEntity, Long> {
}