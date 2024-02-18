package com.example.estdelivery.adapter.out.persistence.user

import com.example.estdelivery.adapter.out.persistence.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_coupon")
class UserCouponEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val couponCode: String,
    val couponConfigId: Long,
    val userId: Long,
) : BaseTimeEntity()