package com.example.estdelivery.application

import com.example.estdelivery.application.port.`in`.IssuePublishedCouponUseCase
import com.example.estdelivery.application.port.`in`.command.IssuePublishedCouponCommand
import com.example.estdelivery.application.port.out.*
import com.example.estdelivery.application.port.out.state.MemberState
import com.example.estdelivery.application.port.out.state.ShopState
import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.domain.shop.Shop

class IssuePublishedCouponService(
    private val loadMemberStatePort: LoadMemberStatePort,
    private val loadCouponStatePort: LoadCouponStatePort,
    private val loadShopOwnerStatePort: LoadShopOwnerStatePort,
    private val updateMemberStatePort: UpdateMemberStatePort,
    private val updateShopStatePort: UpdateShopStatePort,
) : IssuePublishedCouponUseCase {
    /**
     * 1. 회원 정보를 조회한다.
     * 2. 쿠폰정보를 조회한다.
     * 3. 가게가 가진 게시된 쿠폰 북에서 쿠폰을 발행한다.
     * 4. 발급된 쿠폰을 사용자의 쿠폰북에 추가한다.
     * 5. 가게 단골 손님으로 등록한다.
     *
     * @param issuePublishedCouponCommand 발행된 쿠폰을 사용자에게 발급하는 명령
     */
    override fun issuePublishedCoupon(issuePublishedCouponCommand: IssuePublishedCouponCommand) {
        val member = getMember(issuePublishedCouponCommand)
        val shop = getShop(issuePublishedCouponCommand)
        val coupon: Coupon = getCoupon(issuePublishedCouponCommand)
        member.receiveCoupon(shop.issueCoupon(coupon))
        updateMember(member)
        addRoyalCustomers(shop, member)
    }

    private fun addRoyalCustomers(
        shop: Shop,
        member: Member
    ) {
        if (!shop.showRoyalCustomers().contains(member)) {
            shop.addRoyalCustomers(member)
            updateShop(shop)
        }
    }

    private fun updateShop(shop: Shop) {
        updateShopStatePort.update(ShopState.from(shop))
    }

    private fun updateMember(member: Member) {
        updateMemberStatePort.update(MemberState.from(member))
    }

    private fun getShop(issuePublishedCouponCommand: IssuePublishedCouponCommand) =
        loadShopOwnerStatePort.findByShopId(issuePublishedCouponCommand.shopId).toShopOwner().showShop()

    private fun getCoupon(issuePublishedCouponCommand: IssuePublishedCouponCommand) =
        loadCouponStatePort.findByCouponId(issuePublishedCouponCommand.couponId).toCoupon()

    private fun getMember(issuePublishedCouponCommand: IssuePublishedCouponCommand) =
        loadMemberStatePort.findById(issuePublishedCouponCommand.memberId).toMember()
}
