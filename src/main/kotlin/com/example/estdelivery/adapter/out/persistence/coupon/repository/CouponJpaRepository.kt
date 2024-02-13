package com.example.estdelivery.adapter.out.persistence.coupon.repository

import com.example.estdelivery.adapter.out.persistence.coupon.entity.CouponJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CouponJpaRepository : JpaRepository<CouponJpaEntity, Long> {
}
