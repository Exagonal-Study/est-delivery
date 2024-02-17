package com.example.estdelivery.application.port.out.persistence.repository

import com.example.estdelivery.application.port.out.persistence.entity.ShopEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ShopRepository: JpaRepository<ShopEntity, Long>
