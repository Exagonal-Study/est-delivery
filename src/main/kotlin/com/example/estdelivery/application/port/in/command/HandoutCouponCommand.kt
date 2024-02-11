package com.example.estdelivery.application.port.`in`.command

import com.example.estdelivery.domain.coupon.Coupon

class HandoutCouponCommand(val shopOwnerId: Long, val shopId: Long, val coupon: Coupon)
