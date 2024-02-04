package com.example.estdelivery.application.service

import com.example.estdelivery.application.dto.coupon.GenerateCouponCommand
import com.example.estdelivery.application.dto.coupon.CouponResponse
import com.example.estdelivery.application.dto.coupon.IssuedCouponCommand
import com.example.estdelivery.application.port.`in`.coupon.CouponWriteUsecase
import com.example.estdelivery.application.port.out.CouponLogPersistencePort
import com.example.estdelivery.application.port.out.CouponPersistencePort
import com.example.estdelivery.application.port.out.MemberPersistencePort
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val memberPersistencePort: MemberPersistencePort,
    private val couponPersistencePort: CouponPersistencePort,
    private val couponLogPersistencePort: CouponLogPersistencePort
) : CouponWriteUsecase {

    /**
     * 발급을 위한 쿠폰을 생성한다.
     */
    override fun generateCoupon(generateCouponCommand: GenerateCouponCommand): CouponResponse {
        val savedCoupon = couponPersistencePort.generateCoupon(generateCouponCommand.toCouponDomain())
        return CouponResponse(savedCoupon.name, savedCoupon.type, savedCoupon.couponEventType, savedCoupon.getCouponQuantity())
    }

    /**
     * 회원에게 쿠폰을 발급한다.
     * 발급 시, 기존 생성한 쿠폰 수량을 1개 차감한다.
     * 그 후, 발급 로그를 저장한다.
     */
    override fun issuedCoupon(memberId: Long, issuedCouponCommand: IssuedCouponCommand): CouponResponse {
        val member = memberPersistencePort.findMemberById(memberId)
        val coupon = couponPersistencePort.findCouponById(issuedCouponCommand.couponId)
    }

}
