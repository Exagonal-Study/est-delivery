package com.example.estdelivery.application

import com.example.estdelivery.application.port.`in`.HandoutCouponUseCase
import com.example.estdelivery.application.port.`in`.command.HandoutCouponCommand
import com.example.estdelivery.application.port.out.CreateCouponStatePort
import com.example.estdelivery.application.port.out.LoadCouponStatePort
import com.example.estdelivery.application.port.out.LoadShopOwnerStatePort
import com.example.estdelivery.application.port.out.UpdateShopOwnerStatePort
import com.example.estdelivery.application.port.out.state.ShopOwnerState
import com.example.estdelivery.domain.coupon.Coupon

class HandoutCouponService(
    private val loadShopOwnerStatePort: LoadShopOwnerStatePort,
    private val loadCouponStatePort: LoadCouponStatePort,
    private val updateShopOwnerStatePort: UpdateShopOwnerStatePort,
    private val createCouponStatePort: CreateCouponStatePort,
) : HandoutCouponUseCase {
    /**
     * 1. 가게 주인 정보를 조회한다.
     * 2. 가게 정보를 조회한다.
     * 3. 단골들 중 동일한 쿠폰을 아직 받지 않은 사용자에게 쿠폰을 뿌린다.
     *
     * @param handoutCouponCommand 나눠줄 쿠폰 정보와 가게 주인 정보
     */
    override fun handoutCoupon(handoutCouponCommand: HandoutCouponCommand) {
        val shopOwner = loadShopOwnerStatePort.findById(handoutCouponCommand.shopOwnerId).toShopOwner()
        val handoutCoupon: Coupon = getHandoutCoupon(handoutCouponCommand)
        shopOwner.handOutCouponToRoyalCustomersInShop(handoutCoupon)
        updateShopOwnerStatePort.update(ShopOwnerState.from(shopOwner))
    }

    private fun getHandoutCoupon(handoutCouponCommand: HandoutCouponCommand): Coupon {
        val id = handoutCouponCommand.coupon.id
        if (id == null || loadCouponStatePort.exists(id).not()) {
            val handoutCoupon = createCouponStatePort.create(handoutCouponCommand.coupon)
            return handoutCoupon
        }

        val handoutCoupon: Coupon = loadCouponStatePort.findById(id)
        return handoutCoupon
    }
}
