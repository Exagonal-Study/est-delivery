package com.example.estdelivery.adapter.out.persistence.user

import com.example.estdelivery.adapter.out.persistence.coupon.CouponMapper
import com.example.estdelivery.application.domain.model.Coupon
import com.example.estdelivery.application.port.out.FindUserCouponPort
import com.example.estdelivery.application.port.out.IssueUserCouponPort
import com.example.estdelivery.common.CacheType
import com.example.estdelivery.common.annotation.PersistenceAdapter
import com.example.estdelivery.common.utils.DateUtils.atEndOfDay
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import java.time.LocalDate


@PersistenceAdapter
class UserCouponPersistenceAdapter(
    private val userCouponRepository: UserCouponRepository
) : IssueUserCouponPort, FindUserCouponPort {
    @CacheEvict(CacheType.USER_COUPON, key = "{#userId, #coupon.configId}")
    override fun issueUserCoupon(userId: Long, coupon: Coupon) {
        userCouponRepository.save(CouponMapper.toUserCouponEntity(userId, coupon))
    }

    @Cacheable(CacheType.USER_COUPON, key = "{#userId, #coupon.configId}")
    override fun existsIssuedUserCoupon(userId: Long, coupon: Coupon): Boolean {
        val today = LocalDate.now()
        return userCouponRepository.existsByUserIdAndCouponConfigIdAndCreatedAtBetween(
            userId,
            coupon.configId,
            today.atStartOfDay(),
            today.atEndOfDay()
        )
    }

    override fun findByUserId(userId: Long): List<Coupon> =
        userCouponRepository.findByUserId(userId).map { CouponMapper.toCoupon(it) }
}
