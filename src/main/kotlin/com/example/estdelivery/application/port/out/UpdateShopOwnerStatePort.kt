package com.example.estdelivery.application.port.out

import com.example.estdelivery.domain.shop.ShopOwner

interface UpdateShopOwnerStatePort {
    fun update(shopOwner: ShopOwner)
}
