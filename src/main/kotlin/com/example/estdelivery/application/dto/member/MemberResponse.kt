package com.example.estdelivery.application.dto.member

import java.time.LocalDateTime

data class MemberResponse(
    val id: Long,
    val email: String,
    val createdDateTime: LocalDateTime
)
