package com.example.estdelivery.adapter.`in`.web.dto

data class BaseResponseDto<T>(
    val message: String,
    val data: Any? = null
) {
    companion object {
        private const val SUCCESS_MESSAGE = "요청에 성공했습니다."

        fun <T> success(data: Any): BaseResponseDto<T> {
            return BaseResponseDto(
                message = SUCCESS_MESSAGE,
                data = data
            )
        }

        fun <T> success(): BaseResponseDto<T> {
            return BaseResponseDto(
                message = SUCCESS_MESSAGE,
                data = null
            )
        }
    }
}
