package com.example.estdelivery.adapter.out.persistence.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseJpaEntity {

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createAt: LocalDateTime? = null

}
