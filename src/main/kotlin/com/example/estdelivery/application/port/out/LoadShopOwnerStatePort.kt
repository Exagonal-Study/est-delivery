package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.port.out.state.ShopOwnerState

interface LoadShopOwnerStatePort {
    fun findById(shopOwnerId: Long): ShopOwnerState
}
