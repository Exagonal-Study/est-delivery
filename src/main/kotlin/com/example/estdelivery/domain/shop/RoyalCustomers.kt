package com.example.estdelivery.domain.shop

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.member.Member

class RoyalCustomers(
    private val customers: MutableList<Member> = mutableListOf()
) {
    fun handOutCoupon(coupon: Coupon) {
        customers.forEach { it.receiveCoupon(coupon) }
    }

    fun addRoyalCustomers(vararg members: Member) {
        for (member in members) {
            if (customers.contains(member)) {
                throw IllegalArgumentException("이미 등록된 회원입니다.")
            }
        }
        customers.addAll(members)
    }

    fun showRoyalCustomers(): List<Member> {
        return customers.toList()
    }
}
