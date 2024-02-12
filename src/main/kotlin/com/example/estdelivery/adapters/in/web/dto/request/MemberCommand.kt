package com.example.estdelivery.adapters.`in`.web.dto.request

import com.example.estdelivery.domain.Member

data class MemberCommand(
    val name: String = ""
)

fun MemberCommand.toCreateDomain() = Member(name = name)