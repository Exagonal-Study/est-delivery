package com.example.estdelivery.application.port.out.member

import com.example.estdelivery.domain.member.Member

interface MemberPersistencePort {
    fun save(member: Member): Member
}