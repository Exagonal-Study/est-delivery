package com.example.estdelivery.adapters.out.persistence.member.mapper

import com.example.estdelivery.adapters.out.persistence.member.entity.MemberEntity
import com.example.estdelivery.domain.member.Member

object MemberMapper {
    fun fromDomain(member: Member): MemberEntity {
        return MemberEntity(name = member.name)
    }

    fun toDomain(memberEntity: MemberEntity): Member {
        return Member(name = memberEntity.name)
    }
}