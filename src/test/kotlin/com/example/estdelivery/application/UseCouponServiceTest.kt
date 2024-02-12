package com.example.estdelivery.application

import com.example.estdelivery.application.port.`in`.command.UseCouponCommand
import com.example.estdelivery.application.port.out.*
import com.example.estdelivery.application.port.out.state.CouponState
import com.example.estdelivery.application.port.out.state.MemberState
import com.example.estdelivery.application.port.out.state.ShopOwnerState
import com.example.estdelivery.domain.fixture.나눠준_비율_할인_쿠폰
import com.example.estdelivery.domain.fixture.나눠준_쿠폰을_가진_삼건창
import com.example.estdelivery.domain.fixture.나눠준_쿠폰이_있는_프리퍼
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
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
        val memberId = 1L
        val shopOwnerId = 1L
        val shopId = 1L
        val couponId = 나눠준_비율_할인_쿠폰.id!!
        val useCouponCommand = UseCouponCommand(
            memberId,
            shopId,
            couponId
        )

        val 프리퍼_주인_상태 = ShopOwnerState(나눠준_쿠폰이_있는_프리퍼(나눠준_비율_할인_쿠폰), shopOwnerId)
        val 회원 = 나눠준_쿠폰을_가진_삼건창
        val 변경된_회원_상태 = slot<MemberState>()
        val 변경된_프리퍼_주인_상태 = slot<ShopOwnerState>()

        // when
        every { loadMemberStatePort.findById(memberId) } returns MemberState.from(회원)
        every { loadCouponStatePort.findById(couponId) } returns CouponState.from(나눠준_비율_할인_쿠폰)
        every { loadShopOwnerStatePort.findByShopId(shopId) } returns 프리퍼_주인_상태
        every { updateMemberStatePort.update(capture(변경된_회원_상태)) } returns Unit
        every { updateShopOwnerStatePort.update(capture(변경된_프리퍼_주인_상태)) } returns Unit

        useCouponService.useCoupon(useCouponCommand)

        // then
        변경된_회원_상태.captured.toMember().showMyCouponBook() shouldNotContain 나눠준_비율_할인_쿠폰
        변경된_프리퍼_주인_상태.captured.toShopOwner().showUsedCouponBook() shouldContain 나눠준_비율_할인_쿠폰
    }
})
