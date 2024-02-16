package com.example.estdelivery.application.port.out

import com.example.estdelivery.domain.member.Member

interface LoadMemberStatePort {
    fun findById(memberId: Long): Member
}
