package com.example.estdelivery.application.port.out

import com.example.estdelivery.domain.member.Member

interface MemberPersistencePort {
    fun insertMemberOrUpdate(member: Member): Member

    fun findMemberById(memberId: Long): Member
}
