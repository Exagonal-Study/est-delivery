package com.example.estdelivery.common.exception

import com.example.estdelivery.adapter.`in`.web.dto.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(CommonException::class)
    protected fun handleCommonException(exception: CommonException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(exception.errorCode.status)
            .body(
                ErrorResponse(
                    exception.errorCode.code,
                    exception.errorCode.message
                )
            )
}
