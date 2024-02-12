package com.example.estdelivery.adapters.out.persistence.member.repository

import com.example.estdelivery.adapters.out.persistence.member.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<MemberEntity, Long> {
}