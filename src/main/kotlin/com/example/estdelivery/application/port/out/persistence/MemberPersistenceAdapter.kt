package com.example.estdelivery.application.port.out.persistence

import com.example.estdelivery.application.port.out.LoadMemberStatePort
import com.example.estdelivery.application.port.out.UpdateMemberStatePort
import com.example.estdelivery.application.port.out.persistence.mapper.fromMember
import com.example.estdelivery.application.port.out.persistence.mapper.toMember
import com.example.estdelivery.application.port.out.persistence.repository.MemberRepository
import com.example.estdelivery.domain.member.Member
import org.springframework.stereotype.Component

@Component
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository
) : LoadMemberStatePort, UpdateMemberStatePort {
    override fun findById(memberId: Long): Member {
        return toMember(memberRepository.findById(memberId).orElseThrow())
    }

    override fun update(member: Member) {
        memberRepository.save(fromMember(member))
    }
}
