package com.example.estdelivery.application

import com.example.estdelivery.application.port.`in`.command.HandoutCouponCommand
import com.example.estdelivery.application.port.out.*
import com.example.estdelivery.application.port.out.state.CouponState
import com.example.estdelivery.application.port.out.state.ShopOwnerState
import com.example.estdelivery.application.port.out.state.ShopState
import com.example.estdelivery.domain.fixture.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContain
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot

class HandoutCouponServiceTest : FreeSpec({
    val loadShopStatePort = mockk<LoadShopStatePort>()
    val loadShopOwnerStatePort = mockk<LoadShopOwnerStatePort>()
    val loadCouponStatePort = mockk<LoadCouponStatePort>()
    val updateShopOwnerStatePort = mockk<UpdateShopOwnerStatePort>()
    val createCouponStatePort = mockk<CreateCouponStatePort>()

    lateinit var handoutCouponService: HandoutCouponService

    beforeTest {
        handoutCouponService = HandoutCouponService(
            loadShopOwnerStatePort,
            loadShopStatePort,
            loadCouponStatePort,
            updateShopOwnerStatePort,
            createCouponStatePort
        )
    }

    "쿠폰을 나눠줄 수 있다." {
        // given
        val shopOwnerId = 1L
        val shopId = 1L
        val 할인쿠폰_10퍼센트 = 나눠줄_쿠폰
        val 프리퍼_주인_상태 = ShopOwnerState(프리퍼, shopOwnerId)
        val 변경된_프리퍼_주인_상태 = slot<ShopOwnerState>()
        val handoutCouponCommand = HandoutCouponCommand(
            shopOwnerId, shopId,
            할인쿠폰_10퍼센트
        )
        every { loadShopOwnerStatePort.findById(shopOwnerId) } returns 프리퍼_주인_상태

        val 단골_명단 = listOf(일건창, 이건창)
        every { loadShopStatePort.findById(shopId) } returns ShopState(
            가게_이름,
            listOf(),
            listOf(),
            listOf(),
            단골_명단,
            shopId
        )
        every { loadCouponStatePort.exists(any()) } returns false
        every { createCouponStatePort.create(CouponState.from(할인쿠폰_10퍼센트)) } returns CouponState.from(나눠준_비율_할인_쿠폰)
        every { loadCouponStatePort.findById(any()) } returns CouponState.from(나눠준_비율_할인_쿠폰)
        every { updateShopOwnerStatePort.update(capture(변경된_프리퍼_주인_상태)) } returns Unit

        // when
        handoutCouponService.handoutCoupon(handoutCouponCommand)

        // then
        val 가게_주인 = 변경된_프리퍼_주인_상태.captured.toShopOwner()
        가게_주인.showHandOutCouponInShop() shouldContain 나눠준_비율_할인_쿠폰
    }

    "이미 나눠줬던 쿠폰을 다시 나눠줄 때 새로운 단골들에게만 나눠준다." {
        // given
        val shopOwnerId = 1L
        val shopId = 1L
        val 할인쿠폰_10퍼센트 = 나눠줄_쿠폰
        val 프리퍼_주인_상태 = ShopOwnerState(단골이_있는_프리퍼, shopOwnerId)
        val 변경된_프리퍼_주인_상태 = slot<ShopOwnerState>()
        val handoutCouponCommand = HandoutCouponCommand(
            shopOwnerId, shopId,
            할인쿠폰_10퍼센트
        )

        every { loadShopOwnerStatePort.findById(shopOwnerId) } returns 프리퍼_주인_상태
        every { loadShopStatePort.findById(shopId) } returns ShopState.from(
            단골이_있는_프리퍼
        )
        every { loadCouponStatePort.exists(any()) } returns false
        every { createCouponStatePort.create(CouponState.from(할인쿠폰_10퍼센트)) } returns CouponState.from(나눠준_비율_할인_쿠폰)
        every { loadCouponStatePort.findById(any()) } returns CouponState.from(나눠준_비율_할인_쿠폰)
        every { updateShopOwnerStatePort.update(capture(변경된_프리퍼_주인_상태)) } returns Unit

        // when & then
        handoutCouponService.handoutCoupon(handoutCouponCommand)

        val 가게_주인 = 변경된_프리퍼_주인_상태.captured.toShopOwner()
        가게_주인.showHandOutCouponInShop() shouldContain 나눠준_비율_할인_쿠폰
        가게_주인.showRoyalCustomersInShop().map { member ->
            member.showMyCouponBook() shouldContain 나눠준_비율_할인_쿠폰
        }
    }
})
