package com.example.estdelivery.common.exception

data class CommonException(
    val errorCode: ErrorCode,
    val errorCause: String = "",
) : RuntimeException()
