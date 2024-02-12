package com.example.estdelivery.adapters.out.persistence.coupon.adapter

import com.example.estdelivery.adapters.out.persistence.coupon.mapper.CouponMapper
import com.example.estdelivery.adapters.out.persistence.coupon.respository.CouponRepository
import com.example.estdelivery.application.port.out.coupon.GetCouponsQueryPort
import com.example.estdelivery.domain.coupon.Coupon
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class CouponQueryAdapter(
    private val couponRepository: CouponRepository
) : GetCouponsQueryPort {
    override fun getCoupons(): List<Coupon> {
        return couponRepository.findAll().map { CouponMapper.toDomain(it) }
    }
}