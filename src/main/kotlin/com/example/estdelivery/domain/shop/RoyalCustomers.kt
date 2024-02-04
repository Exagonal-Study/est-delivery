package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.member.Member

class RoyalCustomers(
    private val members: MutableList<Member> = mutableListOf()
) {
    fun handOutCoupon(coupon: Coupon) {
        members.forEach { it.receiveCoupon(coupon) }
    }

    fun addRoyalCustomers(vararg member: Member) {
        members.addAll(member)
    }

    fun showRoyalCustomers(): List<Member> {
        return members.toList()
    }
}
