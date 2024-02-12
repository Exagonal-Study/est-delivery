package com.example.estdelivery.application.service

import com.example.estdelivery.adapters.`in`.web.member.dto.request.MemberCommand
import com.example.estdelivery.adapters.`in`.web.member.dto.request.toCreateDomain
import com.example.estdelivery.application.dto.member.CreateMemberResponse
import com.example.estdelivery.application.port.`in`.member.CreateMemberUseCase
import com.example.estdelivery.application.port.out.member.MemberPersistencePort
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberPersistencePort: MemberPersistencePort
) : CreateMemberUseCase {
    override fun createMember(request: MemberCommand): CreateMemberResponse {
        val member = request.toCreateDomain()
        val savedMember = memberPersistencePort.save(member)
        return CreateMemberResponse(savedMember.name)
    }
}