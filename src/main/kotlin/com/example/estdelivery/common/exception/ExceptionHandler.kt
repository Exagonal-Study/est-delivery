package com.example.estdelivery.common.exception

import com.example.estdelivery.application.domain.model.Result
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(CommonException::class)
    protected fun handleCommonException(exception: CommonException): ResponseEntity<Result.Error> =
        ResponseEntity.status(exception.errorCode.status)
            .body(
                Result.Error(
                    exception.errorCode.code,
                    exception.errorCode.message
                )
            )
}
