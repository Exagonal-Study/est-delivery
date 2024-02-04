package com.example.estdelivery.adapter.out.persistence.coupon.adapter

import com.example.estdelivery.application.port.out.CouponLogPersistencePort
import com.example.estdelivery.domain.coupon.CouponLog
import com.example.estdelivery.adapter.out.persistence.coupon.repository.CouponLogJpaRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CouponLogPersistenceAdapter(
    private val couponLogJpaRepository: CouponLogJpaRepository
) : CouponLogPersistencePort {

    override fun saveCouponLog(couponLog: CouponLog): CouponLog {
        TODO("Not yet implemented")
    }

}
