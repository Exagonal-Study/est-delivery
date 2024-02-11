package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.port.out.state.ShopState

interface UpdateShopStatePort {
    fun update(shopState: ShopState)
}
