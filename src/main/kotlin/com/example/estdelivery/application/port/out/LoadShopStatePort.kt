package com.example.estdelivery.application.port.out

import com.example.estdelivery.application.port.out.state.ShopState

interface LoadShopStatePort {
    fun findById(shopId: Long): ShopState
}
