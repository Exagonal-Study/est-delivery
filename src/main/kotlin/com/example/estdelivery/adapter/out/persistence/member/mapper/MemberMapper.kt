package com.example.estdelivery.adapter.out.persistence.member.mapper

import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.adapter.out.persistence.member.entity.MemberJpaEntity

object MemberMapper {

    fun toEntity(member: Member): MemberJpaEntity =
        MemberJpaEntity(
            email = member.email
        )

    fun toDomain(memberJpaEntity: MemberJpaEntity): Member =
        Member(
            id = memberJpaEntity.id,
            email = memberJpaEntity.email,
            createdAt = memberJpaEntity.createAt
        )
}
