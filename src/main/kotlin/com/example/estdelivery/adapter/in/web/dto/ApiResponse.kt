package com.example.estdelivery.adapter.`in`.web.dto

data class ApiResponse<T>(
    val result: T? = null,
    val message: String = "",
)