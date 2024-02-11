package com.example.estdelivery.application

import com.example.estdelivery.application.port.`in`.command.PublishCouponCommand
import com.example.estdelivery.application.port.out.CreateCouponStatePort
import com.example.estdelivery.application.port.out.LoadShopOwnerStatePort
import com.example.estdelivery.application.port.out.LoadShopStatePort
import com.example.estdelivery.application.port.out.UpdateShopOwnerStatePort
import com.example.estdelivery.application.port.out.state.ShopOwnerState
import com.example.estdelivery.application.port.out.state.ShopState
import com.example.estdelivery.domain.fixture.게시할_쿠폰
import com.example.estdelivery.domain.fixture.프리퍼
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot

class PublishCouponServiceTest : FreeSpec({

    val loadShopOwnerPort = mockk<LoadShopOwnerStatePort>()
    val createCouponStatePort = mockk<CreateCouponStatePort>()
    val loadShopStatePort = mockk<LoadShopStatePort>()
    val updateShopOwnerStatePort = mockk<UpdateShopOwnerStatePort>()

    lateinit var publishCouponService: PublishCouponService

    beforeTest {
        publishCouponService = PublishCouponService(
            loadShopStatePort,
            loadShopOwnerPort,
            createCouponStatePort,
            updateShopOwnerStatePort
        )
    }

    "가게 주인은 쿠폰을 게시한다." {
        // given
        val shopOwnerId = 1L
        val shopId = 프리퍼.id!!
        val publishCouponCommand = PublishCouponCommand(
            shopOwnerId,
            shopId,
            게시할_쿠폰
        )

        // when
        val 프리퍼_주인_상태 = ShopOwnerState(프리퍼, shopOwnerId)
        val 변경된_프리퍼_주인_상태 = slot<ShopOwnerState>()

        every { loadShopOwnerPort.findById(shopOwnerId) } returns 프리퍼_주인_상태
        every { loadShopStatePort.findById(shopId) } returns ShopState.from(프리퍼)
        every { createCouponStatePort.create(게시할_쿠폰) } returns Unit
        every { updateShopOwnerStatePort.update(capture(변경된_프리퍼_주인_상태)) } returns Unit

        publishCouponService.publishCoupon(publishCouponCommand)

        // then
        변경된_프리퍼_주인_상태.captured.toShopOwner().showPublishedCouponsInShop() shouldBe listOf(게시할_쿠폰)
    }
})
