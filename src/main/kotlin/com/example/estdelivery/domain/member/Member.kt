package com.example.estdelivery.domain.member

import java.time.LocalDateTime

class Member(
    val id: Long? = null,
    var email: String,
    val createdAt: LocalDateTime? = null
) {
    init {
        require(email.isNotEmpty()) { throw IllegalArgumentException("이메일이 입력되지 않았습니다.") }
        require(validateEmailCheck(email)) { throw IllegalArgumentException("이메일 형식이 올바르지 않습니다.") }
    }

    fun changeEmail(nickname: String) {
        this.email = nickname
    }

    private fun validateEmailCheck(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$".toRegex()
        return emailRegex.matches(email)
    }

}
