package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.port.out.state.ShopOwnerState

interface UpdateShopOwnerStatePort {
    fun update(shopOwnerState: ShopOwnerState)
}
