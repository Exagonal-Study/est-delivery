package com.example.estdelivery.adapters.out.persistence.coupon.entity

import com.example.estdelivery.adapters.out.persistence.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class IssuedCouponEntity(
    memberId: Long,
    couponId: Long
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    var memberId: Long = memberId
        protected set

    var couponId: Long = couponId
        protected set

    var isUsed: Boolean = false
        protected set
}