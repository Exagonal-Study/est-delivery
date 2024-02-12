package com.example.estdelivery.adapters.out.persistence.coupon.adapter

import com.example.estdelivery.adapters.out.persistence.coupon.mapper.CouponMapper
import com.example.estdelivery.adapters.out.persistence.coupon.respository.CouponRepository
import com.example.estdelivery.application.port.out.coupon.CouponQueryPort
import com.example.estdelivery.domain.coupon.Coupon
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Component
@Transactional(readOnly = true)
class CouponQueryAdapter(
    private val couponRepository: CouponRepository
) : CouponQueryPort {
    override fun getCoupons(): List<Coupon> {
        val todayDate = LocalDate.now()
        return couponRepository.findByExpiryDateAfter(todayDate).map { CouponMapper.toDomain(it) }
    }

    override fun getCoupon(couponId: Long): Coupon {
        val couponEntity = couponRepository.findById(couponId)
            .orElseThrow { throw IllegalArgumentException("Coupon not found") }
        return CouponMapper.toDomain(couponEntity)
    }
}