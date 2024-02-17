package com.example.estdelivery.application.port.out

import com.example.estdelivery.domain.shop.ShopOwner

interface LoadShopOwnerStatePort {
    fun findById(shopOwnerId: Long): ShopOwner
    fun findByShopId(shopId: Long): ShopOwner
}
