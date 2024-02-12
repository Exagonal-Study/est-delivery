package com.example.estdelivery.application

import com.example.estdelivery.application.port.`in`.command.IssuePublishedCouponCommand
import com.example.estdelivery.application.port.out.*
import com.example.estdelivery.application.port.out.state.CouponState
import com.example.estdelivery.application.port.out.state.CouponStateAmountType.RATE
import com.example.estdelivery.application.port.out.state.CouponStateType.PUBLISHED
import com.example.estdelivery.application.port.out.state.MemberState
import com.example.estdelivery.application.port.out.state.ShopState
import com.example.estdelivery.domain.fixture.가게_이름
import com.example.estdelivery.domain.fixture.쿠폰_설명
import com.example.estdelivery.domain.fixture.쿠폰_이름
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot

private const val 회원_이름 = "이건창"

/**
 * - 회원은 가게에 게시된 쿠폰북에서 쿠폰을 꺼내 자신의 쿠폰 북에 담는다.
 * - 이미 가진 쿠폰이라면 발행 할 수 없다.
 * - 게시되지 않은 쿠폰은 발행 될 수 없다.
 */
class IssuePublishedCouponServiceTest : FreeSpec({

    val loadCouponStatePort = mockk<LoadCouponStatePort>()
    val loadShopStatePort = mockk<LoadShopStatePort>()
    val loadMemberStatePort = mockk<LoadMemberStatePort>()
    val updateShopStatePort = mockk<UpdateShopStatePort>()
    val updateMemberStatePort = mockk<UpdateMemberStatePort>()

    lateinit var issuePublishedCouponUseCase: IssuePublishedCouponService

    beforeTest {
        issuePublishedCouponUseCase = IssuePublishedCouponService(
            loadMemberStatePort,
            loadCouponStatePort,
            loadShopStatePort,
            updateMemberStatePort,
            updateShopStatePort
        )
    }

    /**
     * state 클래스를 비교해 검증합니다.
     * state는 id를 가지고 비교하게 된다면 상태 변경을 포착하기 어렵습니다.
     * 따라서 state 상태 전부를 비교하여 검증합니다.
     */
    "회원은 가게에 게시된 쿠폰북에서 쿠폰을 꺼내 자신의 쿠폰 북에 담는다." {
        // given
        val memberId = 1L
        val shopId = 1L
        val couponId = 1L
        val issuePublishedCouponCommand = IssuePublishedCouponCommand(couponId, memberId, shopId)
        val 할인쿠폰_10퍼센트_상태 = CouponState(쿠폰_이름, 쿠폰_설명, RATE, PUBLISHED, 10, couponId)
        val 할인쿠폰_10퍼센트 = 할인쿠폰_10퍼센트_상태.toCoupon()

        val 회원_상태 = MemberState(회원_이름, listOf(), memberId)
        val 변경된_가게_상태 = slot<ShopState>()
        val 변경된_회원_상태 = slot<MemberState>()

        every { loadMemberStatePort.findById(memberId) } returns 회원_상태
        every { loadCouponStatePort.findByCouponId(couponId) } returns 할인쿠폰_10퍼센트_상태
        every { loadShopStatePort.findById(shopId) } returns ShopState(
            가게_이름,
            listOf(할인쿠폰_10퍼센트),
            listOf(),
            listOf(),
            listOf()
        )
        every { updateMemberStatePort.update(capture(변경된_회원_상태)) } returns Unit
        every { updateShopStatePort.update(capture(변경된_가게_상태)) } returns Unit

        // when
        issuePublishedCouponUseCase.issuePublishedCoupon(issuePublishedCouponCommand)

        // then
        변경된_회원_상태.captured.toMember().showMyCouponBook() shouldContain 할인쿠폰_10퍼센트
        변경된_가게_상태.captured.toShop().showRoyalCustomers() shouldContain 회원_상태.toMember()
    }

    "이미 가진 쿠폰이라면 담을 수 없다." {
        // given
        val memberId = 1L
        val shopId = 1L
        val couponId = 1L
        val issuePublishedCouponCommand = IssuePublishedCouponCommand(couponId, memberId, shopId)
        val 할인쿠폰_10퍼센트_상태 = CouponState(쿠폰_이름, 쿠폰_설명, RATE, PUBLISHED, 10, couponId)
        val 할인쿠폰_10퍼센트 = 할인쿠폰_10퍼센트_상태.toCoupon()

        val 회원_상태 = MemberState(회원_이름, listOf(할인쿠폰_10퍼센트), memberId)

        every { loadMemberStatePort.findById(memberId) } returns 회원_상태
        every { loadCouponStatePort.findByCouponId(couponId) } returns 할인쿠폰_10퍼센트_상태
        every { updateMemberStatePort.update(any()) } returns Unit
        every { loadShopStatePort.findById(shopId) } returns ShopState(
            가게_이름,
            listOf(할인쿠폰_10퍼센트),
            listOf(),
            listOf(),
            listOf()
        )
        every { updateShopStatePort.update(any()) } returns Unit

        // when & then
        shouldThrow<IllegalArgumentException> {
            issuePublishedCouponUseCase.issuePublishedCoupon(issuePublishedCouponCommand)
        }.message shouldBe "이미 존재하는 쿠폰입니다."
    }

    "게시되지 않은 쿠폰을 받을 수 없다." {
        // given
        val memberId = 1L
        val shopId = 1L
        val couponId = 1L
        val issuePublishedCouponCommand = IssuePublishedCouponCommand(couponId, memberId, shopId)
        val 할인쿠폰_10퍼센트_상태 = CouponState(쿠폰_이름, 쿠폰_설명, RATE, PUBLISHED, 10, couponId)
        val 회원_상태 = MemberState(회원_이름, listOf(), memberId)

        every { loadMemberStatePort.findById(memberId) } returns 회원_상태
        every { loadCouponStatePort.findByCouponId(couponId) } returns 할인쿠폰_10퍼센트_상태
        every { updateMemberStatePort.update(any()) } returns Unit
        every { loadShopStatePort.findById(shopId) } returns ShopState(가게_이름, listOf(), listOf(), listOf(), listOf())
        every { updateShopStatePort.update(any()) } returns Unit

        // when & then
        shouldThrow<IllegalArgumentException> {
            issuePublishedCouponUseCase.issuePublishedCoupon(issuePublishedCouponCommand)
        }.message shouldBe "게시된 쿠폰이 아닙니다."
    }
})
