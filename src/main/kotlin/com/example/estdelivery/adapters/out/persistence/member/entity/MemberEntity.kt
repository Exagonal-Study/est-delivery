package com.example.estdelivery.adapters.out.persistence.member.entity

import com.example.estdelivery.adapters.out.persistence.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class MemberEntity(
    name: String
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    var name: String = name
        protected set
}