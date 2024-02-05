package com.example.estdelivery.application.domain.model

import com.example.estdelivery.common.utils.SnowflakeIdGeneratorUtils

data class Coupon(
    val id: Long? = null,
    var code: String,
    val configId: Long
) {
    companion object {
        fun create(configId: Long): Coupon =
            Coupon(code = SnowflakeIdGeneratorUtils.generateId(1).toString(), configId = configId)
    }
}