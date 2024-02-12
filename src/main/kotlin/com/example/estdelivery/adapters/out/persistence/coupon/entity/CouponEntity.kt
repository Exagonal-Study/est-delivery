package com.example.estdelivery.adapters.out.persistence.coupon.entity

import com.example.estdelivery.adapters.out.persistence.common.BaseTimeEntity
import com.example.estdelivery.common.types.coupon.CouponType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class CouponEntity(
    name: String,
    type: CouponType,
    discountValue: Double,
    expiryDate: LocalDate
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set
    var name: String = name
        protected set
    @Enumerated(EnumType.STRING)
    var type: CouponType = type
        protected set
    var discountValue: Double = discountValue
        protected set
    var expiryDate: LocalDate = expiryDate
        protected set
}
