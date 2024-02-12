package com.example.estdelivery.common.config

import com.example.estdelivery.application.domain.model.User
import com.example.estdelivery.common.Constant.X_AUTHENTICATED_USER
import com.example.estdelivery.common.annotation.UserId
import com.example.estdelivery.common.exception.CommonException
import com.example.estdelivery.common.exception.ErrorCode
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class UserIdArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UserId::class.java) && parameter.parameterType == User::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Any {
        val userId = webRequest.getHeader(X_AUTHENTICATED_USER)?.toLongOrNull()
            ?: throw CommonException(ErrorCode.UNAUTHORIZED)
        return User(userId)
    }
}
