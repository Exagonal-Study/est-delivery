package com.example.estdelivery.adapters.out.persistence.member.adapter

import com.example.estdelivery.adapters.out.persistence.member.mapper.MemberMapper
import com.example.estdelivery.adapters.out.persistence.member.repository.MemberRepository
import com.example.estdelivery.application.port.out.member.MemberPersistencePort
import com.example.estdelivery.domain.member.Member
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository
) : MemberPersistencePort {
    override fun save(member: Member): Member {
        val memberEntity = MemberMapper.fromDomain(member)
        val savedMember = memberRepository.save(memberEntity)
        return MemberMapper.toDomain(savedMember)
    }
}