package com.example.estdelivery.adapter.out.persistence

import com.example.estdelivery.application.domain.model.CouponConfig
import com.example.estdelivery.application.port.out.FindCouponConfigPort
import com.example.estdelivery.common.annotation.PersistenceAdapter
import com.example.estdelivery.common.exception.CommonException
import com.example.estdelivery.common.exception.ErrorCode

@PersistenceAdapter
class CouponConfigPersistenceAdapter(
    private val couponConfigRepository: CouponConfigRepository
) : FindCouponConfigPort {
    override fun findCouponConfig(id: Long): CouponConfig =
        CouponConfigMapper.toCouponConfig(
            couponConfigRepository.findById(id)
                .orElseThrow { CommonException(ErrorCode.NOT_FOUND_COUPON_CONFIG) }
        )
}
