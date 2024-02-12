package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.domain.model.CouponConfig

interface FindCouponConfigPort {
    fun findCouponConfig(id: Long): CouponConfig
}
