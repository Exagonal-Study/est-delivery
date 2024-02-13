package com.example.estdelivery.adapter.out.persistence.member.repository

import com.example.estdelivery.adapter.out.persistence.member.entity.MemberJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<MemberJpaEntity, Long>
