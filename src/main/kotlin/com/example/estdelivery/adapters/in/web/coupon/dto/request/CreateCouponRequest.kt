package com.example.estdelivery.adapters.`in`.web.coupon.dto.request

import com.example.estdelivery.common.types.coupon.CouponType
import com.example.estdelivery.domain.coupon.Coupon
import java.time.LocalDate

data class CreateCouponRequest(
    val name: String,
    val type: CouponType,
    val discountValue: Double,
    val expiryDate: LocalDate
)

fun CreateCouponRequest.toDomain(): Coupon = Coupon(
    name = this.name,
    type = this.type,
    discountValue = this.discountValue,
    expiryDate = this.expiryDate
)
