package com.example.estdelivery.application.port.out

import com.example.estdelivery.domain.member.Member


interface UpdateMemberStatePort {
    fun update(member: Member)
}
