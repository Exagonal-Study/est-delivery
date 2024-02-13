package com.example.estdelivery.application.port.`in`.member

import com.example.estdelivery.application.dto.member.MemberResponse

interface MemberReadUsecase {
    fun getMemberById(memberId: Long): MemberResponse
}
