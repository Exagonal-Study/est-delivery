package com.example.estdelivery.adapter.out.persistence.member.adapter

import com.example.estdelivery.application.port.out.MemberPersistencePort
import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.adapter.out.persistence.member.mapper.MemberMapper
import com.example.estdelivery.adapter.out.persistence.member.repository.MemberJpaRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class MemberPersistenceAdapter(
    private val memberJpaRepository: MemberJpaRepository
) : MemberPersistencePort {
    override fun insertMemberOrUpdate(member: Member): Member {
        val memberJpaEntity = MemberMapper.toEntity(member)
        val saveMember = memberJpaRepository.save(memberJpaEntity)
        return MemberMapper.toDomain(saveMember)
    }

    @Transactional(readOnly = true)
    override fun findMemberById(memberId: Long): Member {
        val findMember = memberJpaRepository.findById(memberId)
            .orElseThrow { throw RuntimeException("Member not found") }

        return MemberMapper.toDomain(findMember)
    }

}
