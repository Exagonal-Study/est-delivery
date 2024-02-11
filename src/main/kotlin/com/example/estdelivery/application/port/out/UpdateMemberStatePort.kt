package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.port.out.state.MemberState

interface UpdateMemberStatePort {
    fun updateMember(memberState: MemberState)
}