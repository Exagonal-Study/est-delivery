package com.example.estdelivery.application.port.out.persistence.repository

import com.example.estdelivery.application.port.out.persistence.entity.ShopEntity
import com.example.estdelivery.application.port.out.persistence.entity.ShopOwnerEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ShopOwnerRepository : JpaRepository<ShopOwnerEntity, Long> {
    fun findByShopEntity(shopEntity: ShopEntity): Optional<ShopOwnerEntity>
}
