package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.port.out.state.MemberState

interface LoadMemberStatePort {
    fun findById(memberId: Long): MemberState
}
