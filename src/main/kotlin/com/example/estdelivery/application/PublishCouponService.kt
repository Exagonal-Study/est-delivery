package com.example.estdelivery.application

import com.example.estdelivery.application.port.`in`.PublishCouponUseCase
import com.example.estdelivery.application.port.`in`.command.PublishCouponCommand
import com.example.estdelivery.application.port.out.CreateCouponStatePort
import com.example.estdelivery.application.port.out.LoadShopOwnerStatePort
import com.example.estdelivery.application.port.out.LoadShopStatePort
import com.example.estdelivery.application.port.out.UpdateShopOwnerStatePort
import com.example.estdelivery.application.port.out.state.ShopOwnerState

class PublishCouponService(
    private val loadShopStatePort: LoadShopStatePort,
    private val loadShopOwnerPort: LoadShopOwnerStatePort,
    private val createCouponStatePort: CreateCouponStatePort,
    private val updateShopOwnerStatePort: UpdateShopOwnerStatePort,
) : PublishCouponUseCase {
    /**
     * 1. 가게 주인 정보를 조회한다.
     * 2. 가게 정보를 조회한다.
     * 3. 쿠폰을 생성한다.
     * 4. 가게에 게시된 쿠폰북에 게시한다.
     *
     * @param publishCouponCommand 게시할 쿠폰 정보와 가게 주인 정보
     */
    override fun publishCoupon(publishCouponCommand: PublishCouponCommand) {
        val shopOwner = loadShopOwnerPort.findById(publishCouponCommand.shopOwnerId).toShopOwner()
        val shop = loadShopStatePort.findById(publishCouponCommand.shopId).toShop()

        if (shopOwner.isOwn(shop).not()) {
            throw IllegalArgumentException("가게 주인이 아닙니다.")
        }

        shopOwner.publishCouponInShop(publishCouponCommand.coupon)

        createCouponStatePort.create(publishCouponCommand.coupon)
        updateShopOwnerStatePort.update(ShopOwnerState.from(shopOwner))
    }
}
