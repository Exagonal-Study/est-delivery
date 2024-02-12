package com.example.estdelivery.application.port.`in`.member

import com.example.estdelivery.adapters.`in`.web.member.dto.request.MemberCommand
import com.example.estdelivery.application.dto.member.CreateMemberResponse

interface CreateMemberUseCase {
    fun createMember(request: MemberCommand): CreateMemberResponse
}