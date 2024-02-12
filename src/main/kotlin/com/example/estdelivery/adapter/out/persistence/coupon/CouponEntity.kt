package com.example.estdelivery.adapter.out.persistence.coupon

import com.example.estdelivery.adapter.out.persistence.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "coupon")
class CouponEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val code: String,
    val configId: Long,
) : BaseTimeEntity()