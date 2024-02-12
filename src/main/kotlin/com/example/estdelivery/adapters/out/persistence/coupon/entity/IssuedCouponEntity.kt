package com.example.estdelivery.adapters.out.persistence.coupon.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "issued_coupon")
class IssuedCouponEntity(
    memberId: Long,
    couponId: Long
) {
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