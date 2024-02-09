package com.example.estdelivery.domain.member

import com.example.estdelivery.domain.coupon.Coupon

class Member(
    val id: Long,
    val name: String,
    private val unusedCouponBook: UnusedCouponBook
) {
    fun useCoupon(coupon: Coupon) {
        unusedCouponBook.removeUsedCoupon(coupon)
    }

    fun showMyCouponBook(): List<Coupon> {
        return unusedCouponBook.showUnusedCoupons()
    }

    fun receiveCoupon(coupon: Coupon) {
        unusedCouponBook.addUnusedCoupon(coupon)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Member(id=$id, name='$name', unUsedCouponBook=$unusedCouponBook)"
    }
}
