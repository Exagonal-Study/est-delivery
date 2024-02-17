package com.example.estdelivery.application.port.out.persistence

import com.example.estdelivery.application.port.out.LoadShopOwnerStatePort
import com.example.estdelivery.application.port.out.UpdateShopOwnerStatePort
import com.example.estdelivery.application.port.out.persistence.mapper.fromShopOwner
import com.example.estdelivery.application.port.out.persistence.mapper.toShopOwner
import com.example.estdelivery.application.port.out.persistence.repository.ShopOwnerRepository
import com.example.estdelivery.application.port.out.persistence.repository.ShopRepository
import com.example.estdelivery.domain.shop.ShopOwner
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class ShopOwnerPersistenceAdapter(
    private val shopOwnerRepository: ShopOwnerRepository,
    private val shopRepository: ShopRepository
) : LoadShopOwnerStatePort, UpdateShopOwnerStatePort {
    override fun findById(shopOwnerId: Long): ShopOwner {
        val shopOwnerEntity = shopOwnerRepository.findById(shopOwnerId).orElseThrow()
        return toShopOwner(shopOwnerEntity)
    }

    override fun findByShopId(shopId: Long): ShopOwner {
        val shopEntity = shopRepository.findById(shopId).orElseThrow()
        val shopOwnerEntity = shopOwnerRepository.findByShopEntity(shopEntity).orElseThrow()
        return toShopOwner(shopOwnerEntity)
    }

    @Transactional
    override fun update(shopOwner: ShopOwner) {
        shopOwnerRepository.save(fromShopOwner(shopOwner))
    }
}
