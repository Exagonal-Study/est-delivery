package com.example.estdelivery.application.port.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "coupon")
class CouponEntity(
    var name: String,
    var description: String,
    @Enumerated(EnumType.STRING)
    var amountType: CouponStateAmountType,
    @Enumerated(EnumType.STRING)
    var type: CouponStateType,
    var amount: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
)
