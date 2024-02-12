package com.example.estdelivery.adapters.out.persistence.common

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {
    @CreatedDate
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
}