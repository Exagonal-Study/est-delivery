package com.example.estdelivery.domain.generator

import com.example.estdelivery.domain.coupon.CouponNumber
import com.github.f4b6a3.tsid.TsidCreator

object CouponIdGenerator {

    fun generate(): CouponNumber = CouponNumber(
        TsidCreator.getTsid().toString()
    )

}
