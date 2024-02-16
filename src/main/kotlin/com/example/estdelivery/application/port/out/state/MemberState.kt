package com.example.estdelivery.application.port.out.state

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.domain.member.UnusedCouponBook

data class MemberState(
    private val name: String,
    private val unusedCoupons: List<Coupon>,
    private val id: Long? = null,
) {
    fun toMember(): Member {
        return Member(
            id!!,
            name,
            UnusedCouponBook(CouponBook(unusedCoupons))
        )
    }

    companion object {
        fun from(member: Member): MemberState {
            return MemberState(
                member.name,
                member.showMyCouponBook(),
                member.id
            )
        }
    }


}
