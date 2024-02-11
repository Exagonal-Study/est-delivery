package com.example.estdelivery.application.port.out.state

import com.example.estdelivery.domain.shop.Shop
import com.example.estdelivery.domain.shop.ShopOwner

data class ShopOwnerState(
    private val shop: Shop,
    private val id: Long? = null
) {

    fun toShopOwner(): ShopOwner {
        return ShopOwner(shop, id!!)
    }

    companion object {
        fun from(shopOwner: ShopOwner): ShopOwnerState {
            return ShopOwnerState(
                shopOwner.showShop(),
                shopOwner.id
            )
        }
    }
}
