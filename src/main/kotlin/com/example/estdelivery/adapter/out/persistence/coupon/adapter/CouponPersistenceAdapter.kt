package com.example.estdelivery.adapter.out.persistence.coupon.adapter

import com.example.estdelivery.application.port.out.CouponPersistencePort
import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.adapter.out.persistence.coupon.mapper.CouponMapper
import com.example.estdelivery.adapter.out.persistence.coupon.repository.CouponJpaRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CouponPersistenceAdapter(
    private val couponJpaRepository: CouponJpaRepository
) : CouponPersistencePort {
    override fun generateCoupon(coupon: Coupon): Coupon {
        val savedCoupon = couponJpaRepository.save(CouponMapper.toEntity(coupon))
        return CouponMapper.toDomain(savedCoupon)
    }

    @Transactional(readOnly = true)
    override fun findCouponById(couponId: Long): Coupon {
        val findCoupon = couponJpaRepository.findById(couponId)
            .orElseThrow { throw RuntimeException("쿠폰이 존재하지 않습니다.") }

        return CouponMapper.toDomain(findCoupon)
    }

}
