package com.example.estdelivery.application.port.`in`

import com.example.estdelivery.application.port.`in`.command.UseCouponCommand

interface UseCouponUseCase {
    fun useCoupon(useCouponCommand: UseCouponCommand)
}