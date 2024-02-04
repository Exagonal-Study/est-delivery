package com.example.estdelivery.application.dto.member

import com.example.estdelivery.domain.member.Member

data class RegisterMemberCommand(
    val email: String
) {
    fun toDomain(): Member = Member(email = email)
}
