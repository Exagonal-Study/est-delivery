package com.example.estdelivery.adapter.out.persistence.member.entity

import com.example.estdelivery.adapter.out.persistence.common.BaseJpaEntity
import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberJpaEntity(
    email: String
) : BaseJpaEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(name = "member_name", nullable = false, length = 30)
    var email: String = email
        protected set
}
