package com.example.estdelivery.application

import com.example.estdelivery.application.port.`in`.command.PublishCouponCommand
import com.example.estdelivery.application.port.out.CreateCouponStatePort
import com.example.estdelivery.application.port.out.LoadShopOwnerStatePort
import com.example.estdelivery.application.port.out.LoadShopStatePort
import com.example.estdelivery.application.port.out.UpdateShopOwnerStatePort
import com.example.estdelivery.application.port.out.state.CouponState
import com.example.estdelivery.application.port.out.state.ShopOwnerState
import com.example.estdelivery.application.port.out.state.ShopState
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.fixture.게시된_고정_할인_쿠폰
import com.example.estdelivery.domain.fixture.게시할_쿠폰
import com.example.estdelivery.domain.fixture.새로_창업해서_아무것도_없는_프리퍼
import com.example.estdelivery.domain.shop.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot

class PublishCouponServiceTest : FreeSpec({
    val loadShopOwnerPort = mockk<LoadShopOwnerStatePort>()
    val createCouponStatePort = mockk<CreateCouponStatePort>()
    val updateShopOwnerStatePort = mockk<UpdateShopOwnerStatePort>()

    lateinit var publishCouponService: PublishCouponService

    beforeTest {
        publishCouponService = PublishCouponService(
            loadShopOwnerPort,
            createCouponStatePort,
            updateShopOwnerStatePort
        )
    }

    "가게 주인은 쿠폰을 게시한다." {
        // given
        val shopOwnerId = 1L
        val shopId = 새로_창업해서_아무것도_없는_프리퍼.id!!
        val publishCouponCommand = PublishCouponCommand(
            shopOwnerId,
            shopId,
            게시할_쿠폰
        )
        val 프리퍼_주인_상태 = ShopOwnerState(새로_창업해서_아무것도_없는_프리퍼, shopOwnerId)
        val 변경된_프리퍼_주인_상태 = slot<ShopOwnerState>()

        // when
        every { loadShopOwnerPort.findById(shopOwnerId) } returns 프리퍼_주인_상태
        every { createCouponStatePort.create(CouponState.from(게시할_쿠폰)) } returns CouponState.from(게시된_고정_할인_쿠폰)
        every { updateShopOwnerStatePort.update(capture(변경된_프리퍼_주인_상태)) } returns Unit

        publishCouponService.publishCoupon(publishCouponCommand)

        // then
        변경된_프리퍼_주인_상태.captured.toShopOwner().showPublishedCouponsInShop() shouldContain 게시된_고정_할인_쿠폰
    }

    "게시된 쿠폰북에 동일한 쿠폰이 있을 수 없다." {
        // given
        val shopOwnerId = 1L
        val shopId = 새로_창업해서_아무것도_없는_프리퍼.id!!
        val publishCouponCommand = PublishCouponCommand(
            shopOwnerId,
            shopId,
            게시할_쿠폰
        )
        val 이미_쿠폰을_게시한_프리퍼 = Shop(
            PublishedCouponBook(CouponBook(listOf(게시된_고정_할인_쿠폰))),
            HandOutCouponBook(CouponBook(listOf())),
            UsedCouponBook(CouponBook(listOf())),
            RoyalCustomers(listOf()),
            "프리퍼",
            shopId
        )
        val 프리퍼_주인_상태 = ShopOwnerState(이미_쿠폰을_게시한_프리퍼, shopOwnerId)

        // when

        every { loadShopOwnerPort.findById(shopOwnerId) } returns 프리퍼_주인_상태
        every { createCouponStatePort.create(CouponState.from(게시할_쿠폰)) } returns CouponState.from(게시된_고정_할인_쿠폰)

        // then
        shouldThrow<IllegalArgumentException> {
            publishCouponService.publishCoupon(publishCouponCommand)
        }.message shouldBe "이미 게시한 쿠폰입니다."
    }
})
