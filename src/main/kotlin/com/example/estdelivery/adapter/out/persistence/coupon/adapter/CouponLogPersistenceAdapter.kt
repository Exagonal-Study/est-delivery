package com.example.estdelivery.adapter.out.persistence.coupon.adapter

import com.example.estdelivery.adapter.out.persistence.coupon.mapper.CouponLogMapper
import com.example.estdelivery.application.port.out.CouponLogPersistencePort
import com.example.estdelivery.domain.coupon.CouponLog
import com.example.estdelivery.adapter.out.persistence.coupon.repository.CouponLogJpaRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CouponLogPersistenceAdapter(
    private val couponLogJpaRepository: CouponLogJpaRepository
) : CouponLogPersistencePort {

    @Transactional
    override fun saveCouponLog(couponLog: CouponLog): CouponLog {
        val couponLogEntity = CouponLogMapper.toEntity(couponLog)
        val savedEntity = couponLogJpaRepository.save(couponLogEntity)

        return CouponLogMapper.toDomain(savedEntity)
    }

    @Transactional(readOnly = true)
    override fun findCouponLogByIdAndMemberId(couponId: String, memberId: Long): CouponLog {
        val couponLogJpaEntity = couponLogJpaRepository.findByCouponIdAndUserId(couponId, memberId)
            .orElseThrow { throw RuntimeException("쿠폰 발급 이력이 존재하지 않습니다.") }

        return CouponLogMapper.toDomain(couponLogJpaEntity)
    }

}
