package com.example.estdelivery.application

import com.example.estdelivery.application.port.`in`.command.UseCouponCommand
import com.example.estdelivery.application.port.out.*
import com.example.estdelivery.application.port.out.state.CouponState
import com.example.estdelivery.application.port.out.state.MemberState
import com.example.estdelivery.application.port.out.state.ShopOwnerState
import com.example.estdelivery.domain.fixture.나눠준_비율_할인_쿠폰
import com.example.estdelivery.domain.fixture.나눠준_쿠폰을_가진_삼건창
import com.example.estdelivery.domain.fixture.나눠준_쿠폰이_있는_프리퍼
import com.example.estdelivery.domain.fixture.새로_창업해서_아무것도_없는_프리퍼
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot

class UseCouponServiceTest : FreeSpec({

    val loadMemberStatePort = mockk<LoadMemberStatePort>()
    val loadShopOwnerStatePort = mockk<LoadShopOwnerStatePort>()
    val loadCouponStatePort = mockk<LoadCouponStatePort>()
    val updateMemberStatePort = mockk<UpdateMemberStatePort>()
    val updateShopOwnerStatePort = mockk<UpdateShopOwnerStatePort>()

    lateinit var useCouponService: UseCouponService

    beforeTest {
        useCouponService = UseCouponService(
            loadMemberStatePort,
            loadCouponStatePort,
            loadShopOwnerStatePort,
            updateMemberStatePort,
            updateShopOwnerStatePort
        )
    }

    "회원은 가게에 쿠폰을 사용할 수 있다." {
        // given
        val 프리퍼_주인_상태 = ShopOwnerState(나눠준_쿠폰이_있는_프리퍼(나눠준_비율_할인_쿠폰), 1L)
        val 회원 = 나눠준_쿠폰을_가진_삼건창()
        val memberId = 회원.id
        val shopId = 프리퍼_주인_상태.toShopOwner().showShop().id!!
        val couponId = 나눠준_비율_할인_쿠폰.id!!
        val useCouponCommand = UseCouponCommand(
            memberId,
            shopId,
            couponId
        )
        val 변경된_회원_상태 = slot<MemberState>()
        val 변경된_프리퍼_주인_상태 = slot<ShopOwnerState>()

        // when
        every { loadMemberStatePort.findById(memberId) } returns MemberState.from(회원)
        every { loadCouponStatePort.findById(couponId) } returns 나눠준_비율_할인_쿠폰
        every { loadShopOwnerStatePort.findByShopId(shopId) } returns 프리퍼_주인_상태
        every { updateMemberStatePort.update(capture(변경된_회원_상태)) } returns Unit
        every { updateShopOwnerStatePort.update(capture(변경된_프리퍼_주인_상태)) } returns Unit

        useCouponService.useCoupon(useCouponCommand)

        // then
        변경된_회원_상태.captured.toMember().showMyCouponBook() shouldNotContain 나눠준_비율_할인_쿠폰
        변경된_프리퍼_주인_상태.captured.toShopOwner().showUsedCouponBook() shouldContain 나눠준_비율_할인_쿠폰
    }

    "이미 사용한 쿠폰은 사용할 수 없다." {
        // given
        val 프리퍼_주인_상태 = ShopOwnerState(나눠준_쿠폰이_있는_프리퍼(나눠준_비율_할인_쿠폰), 1L)
        val 회원 = 나눠준_쿠폰을_가진_삼건창()
        val memberId = 회원.id
        val shopId = 프리퍼_주인_상태.toShopOwner().showShop().id!!
        val couponId = 나눠준_비율_할인_쿠폰.id!!
        val useCouponCommand = UseCouponCommand(
            memberId,
            shopId,
            couponId
        )

        // when
        회원.useCoupon(나눠준_비율_할인_쿠폰)

        every { loadMemberStatePort.findById(memberId) } returns MemberState.from(회원)
        every { loadCouponStatePort.findById(couponId) } returns 나눠준_비율_할인_쿠폰
        every { loadShopOwnerStatePort.findByShopId(shopId) } returns 프리퍼_주인_상태
        every { updateMemberStatePort.update(any()) } returns Unit
        every { updateShopOwnerStatePort.update(any()) } returns Unit

        // then
        shouldThrow<IllegalArgumentException> {
            useCouponService.useCoupon(useCouponCommand)
        }.message shouldBe "존재하지 않는 쿠폰입니다."
    }

    "게시되지 않거나 나눠주지 않은 쿠폰은 사용할 수 없다." {
        // given
        val 프리퍼_주인_상태 = ShopOwnerState(새로_창업해서_아무것도_없는_프리퍼(), 1L)
        val 회원 = 나눠준_쿠폰을_가진_삼건창()
        val memberId = 회원.id
        val shopId = 프리퍼_주인_상태.toShopOwner().showShop().id!!
        val couponId = 나눠준_비율_할인_쿠폰.id!!
        val useCouponCommand = UseCouponCommand(
            memberId,
            shopId,
            couponId
        )

        // when
        every { loadMemberStatePort.findById(memberId) } returns MemberState.from(회원)
        every { loadCouponStatePort.findById(couponId) } returns 나눠준_비율_할인_쿠폰
        every { loadShopOwnerStatePort.findByShopId(shopId) } returns 프리퍼_주인_상태
        every { updateMemberStatePort.update(any()) } returns Unit
        every { updateShopOwnerStatePort.update(any()) } returns Unit

        // then
        shouldThrow<IllegalArgumentException> {
            useCouponService.useCoupon(useCouponCommand)
        }.message shouldBe "게시하거나 나눠주지 않은 쿠폰입니다."
    }
})
