package com.example.estdelivery.adapter.out.persistence.coupon.entity

import com.example.estdelivery.domain.coupon.types.CouponType
import com.example.estdelivery.adapter.out.persistence.common.BaseJpaEntity
import com.example.estdelivery.domain.coupon.types.CouponEventType
import jakarta.persistence.*

@Entity
@Table(name = "coupon")
class CouponJpaEntity(
    couponName: String,
    couponQuantity: Long,
    couponType: CouponType,
    couponEventType: CouponEventType
) : BaseJpaEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(name = "coupon_name", nullable = false, length = 80)
    var name: String = couponName
        protected set

    @Column(name = "coupon_quantity", nullable = false)
    var quantity: Long = couponQuantity
        protected set

    @Column(name = "coupon_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var type: CouponType = couponType
        protected set

    @Column(name = "coupon_event_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var couponEventType: CouponEventType = couponEventType
        protected set

    fun updateCouponQuantity(quatity: Long) {
        this.quantity = quatity
    }


}
