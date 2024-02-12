package com.example.estdelivery.application.domain.model

sealed class Result {
    data class Success<T>(
        val data: T? = null,
        val message: String = ""
    )

    data class Error(
        val code: Int,
        val message: String
    )
}