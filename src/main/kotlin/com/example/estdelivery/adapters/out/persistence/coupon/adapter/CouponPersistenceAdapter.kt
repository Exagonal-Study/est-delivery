package com.example.estdelivery.adapters.out.persistence.coupon.adapter

import com.example.estdelivery.adapters.out.persistence.coupon.entity.IssuedCouponEntity
import com.example.estdelivery.adapters.out.persistence.coupon.mapper.CouponMapper
import com.example.estdelivery.adapters.out.persistence.coupon.respository.CouponRepository
import com.example.estdelivery.adapters.out.persistence.coupon.respository.IssueCouponRepository
import com.example.estdelivery.application.port.out.coupon.CouponPersistencePort
import com.example.estdelivery.domain.coupon.Coupon
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CouponPersistenceAdapter(
    private val couponRepository: CouponRepository,
    private val issueCouponRepository: IssueCouponRepository
) : CouponPersistencePort {
    override fun createCoupon(coupon: Coupon): Coupon {
        val couponEntity = CouponMapper.fromDomain(coupon)
        val issuedCoupon = couponRepository.save(couponEntity)
        return CouponMapper.toDomain(issuedCoupon)
    }

    override fun issueCoupon(memberId: Long, couponId: Long) {
        issueCouponRepository.save(IssuedCouponEntity(memberId, couponId))
    }
}