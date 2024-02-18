package com.example.estdelivery.adapter.out.persistence.coupon

import com.example.estdelivery.adapter.out.persistence.BaseTimeEntity
import com.example.estdelivery.application.domain.model.CouponType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "coupon_configuration")
class CouponConfigEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Enumerated(EnumType.STRING)
    val type: CouponType,
    val json: String,
) : BaseTimeEntity()