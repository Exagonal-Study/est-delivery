package com.example.estdelivery.adapter.out.persistence.coupon

import com.example.estdelivery.application.domain.model.Coupon
import com.example.estdelivery.application.port.out.CreateCouponPort
import com.example.estdelivery.common.annotation.PersistenceAdapter

@PersistenceAdapter
class CouponPersistenceAdapter(
    private val couponRepository: CouponRepository
) : CreateCouponPort {
    override fun createCoupon(coupon: Coupon): Coupon =
        CouponMapper.toCoupon(couponRepository.save(CouponMapper.toCouponEntity(coupon)))
}
