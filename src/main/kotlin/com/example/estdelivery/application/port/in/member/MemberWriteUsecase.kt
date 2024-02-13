package com.example.estdelivery.application.port.`in`.member

import com.example.estdelivery.application.dto.member.MemberResponse
import com.example.estdelivery.application.dto.member.RegisterMemberCommand

interface MemberWriteUsecase {
    fun registerMember(registerMemberCommand: RegisterMemberCommand): MemberResponse
}
