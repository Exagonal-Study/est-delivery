package com.example.estdelivery.adapter.out.persistence.coupon.entity

import com.example.estdelivery.adapter.out.persistence.common.BaseJpaEntity
import jakarta.persistence.*

@Entity
@Table(name = "coupon_log")
class CouponLogJpaEntity(
    couponId: String,
    userId: Long,
    issued: Boolean = true
) : BaseJpaEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(name = "coupon_id", nullable = false)
    var couponId: String = couponId
        protected set

    @Column(name = "user_id", nullable = false)
    var userId: Long = userId
        protected set

    @Column(name = "issued", nullable = false)
    var issued: Boolean = issued
        protected set

}
