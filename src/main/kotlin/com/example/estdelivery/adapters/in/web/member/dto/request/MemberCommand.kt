package com.example.estdelivery.adapters.`in`.web.member.dto.request

import com.example.estdelivery.domain.member.Member

data class MemberCommand(
    val name: String = ""
)

fun MemberCommand.toCreateDomain() = Member(name = name)