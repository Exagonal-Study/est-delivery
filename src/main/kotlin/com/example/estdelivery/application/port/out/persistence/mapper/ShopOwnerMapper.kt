package com.example.estdelivery.application.port.out.persistence.mapper

import com.example.estdelivery.application.port.out.persistence.entity.ShopEntity
import com.example.estdelivery.application.port.out.persistence.entity.ShopOwnerEntity
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.shop.HandOutCouponBook
import com.example.estdelivery.domain.shop.PublishedCouponBook
import com.example.estdelivery.domain.shop.RoyalCustomers
import com.example.estdelivery.domain.shop.Shop
import com.example.estdelivery.domain.shop.ShopOwner
import com.example.estdelivery.domain.shop.UsedCouponBook

internal fun toShopOwner(entity: ShopOwnerEntity): ShopOwner {
    return ShopOwner(toShop(entity.shopEntity), entity.id!!)
}

internal fun fromShopOwner(shopOwner: ShopOwner): ShopOwnerEntity {
    return ShopOwnerEntity(
        fromShop(shopOwner.showShop()),
        shopOwner.id
    )
}

internal fun toShop(entity: ShopEntity): Shop {
    return Shop(
        PublishedCouponBook(CouponBook(entity.publishedCouponBook.map { toCoupon(it) })),
        HandOutCouponBook(CouponBook(entity.handOutCouponBook.map { toCoupon(it) })),
        UsedCouponBook(CouponBook(entity.usedCouponBook.map { toCoupon(it) })),
        RoyalCustomers(entity.royalCustomers.map { toMember(it) }),
        entity.name,
        entity.id!!
    )
}

internal fun fromShop(shop: Shop): ShopEntity {
    return ShopEntity(
        shop.showPublishedCoupons().map { fromCoupon(it) },
        shop.showHandOutCoupon().map { fromCoupon(it) },
        shop.showUsedCoupons().map { fromCoupon(it) },
        shop.showRoyalCustomers().map { fromMember(it) },
        shop.name,
        shop.id
    )
}
