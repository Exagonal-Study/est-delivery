package com.example.estdelivery.domain.generator

import com.github.f4b6a3.tsid.TsidCreator

object CouponIdGenerator {

    fun generate(): String {
        return TsidCreator.getTsid().toString()
    }
}
