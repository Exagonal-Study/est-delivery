package com.example.estdelivery.application.domain.service

import com.example.estdelivery.application.domain.model.Coupon
import com.example.estdelivery.application.port.`in`.IssueCouponCommand
import com.example.estdelivery.application.port.`in`.IssueCouponUseCase
import com.example.estdelivery.application.port.out.CreateCouponPort
import com.example.estdelivery.application.port.out.FindCouponConfigPort
import com.example.estdelivery.application.port.out.FindUserCouponPort
import com.example.estdelivery.application.port.out.IssueUserCouponPort
import com.example.estdelivery.common.exception.CommonException
import com.example.estdelivery.common.exception.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class IssueCouponService(
    val findCouponConfigPort: FindCouponConfigPort,
    val createCouponPort: CreateCouponPort,
    val issueUserCouponPort: IssueUserCouponPort,
    val findUserCouponPort: FindUserCouponPort
) : IssueCouponUseCase {
    override fun issueCoupon(issueCouponCommand: IssueCouponCommand) {
        val userId = issueCouponCommand.userId

        // 발급 요청 쿠폰에 해당하는 설정 값 조회
        val couponConfig = findCouponConfigPort.findCouponConfig(issueCouponCommand.couponConfigId)

        // 쿠폰 생성
        val coupon = Coupon.create(couponConfig.id)

        // 당일 동일 쿠폰 발급 여부 확인
        if (findUserCouponPort.existsIssuedUserCoupon(userId, coupon)) {
            throw CommonException(ErrorCode.DUPLICATED_COUPON)
        }

        // 쿠폰 발급
        createCouponPort.createCoupon(coupon)

        // 사용자에게 할당
        issueUserCouponPort.issueUserCoupon(userId, coupon)
    }
}