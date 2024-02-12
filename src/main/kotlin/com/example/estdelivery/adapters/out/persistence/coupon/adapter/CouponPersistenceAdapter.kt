package com.example.estdelivery.adapters.out.persistence.coupon.adapter

import com.example.estdelivery.adapters.out.persistence.coupon.mapper.CouponMapper
import com.example.estdelivery.adapters.out.persistence.coupon.respository.CouponRepository
import com.example.estdelivery.application.port.out.coupon.CouponPersistencePort
import com.example.estdelivery.domain.coupon.Coupon
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CouponPersistenceAdapter(
    private val couponRepository: CouponRepository
) : CouponPersistencePort {
    override fun save(coupon: Coupon): Coupon {
        val couponEntity = CouponMapper.fromDomain(coupon)
        val issuedCoupon = couponRepository.save(couponEntity)
        return CouponMapper.toDomain(issuedCoupon)
    }
}