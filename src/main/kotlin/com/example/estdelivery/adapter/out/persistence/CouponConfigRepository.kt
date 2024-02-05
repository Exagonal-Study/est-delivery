package com.example.estdelivery.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface CouponConfigRepository : JpaRepository<CouponConfigEntity, Long> {
}
