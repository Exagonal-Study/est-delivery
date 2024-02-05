package com.example.estdelivery.domain.member

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook

class Member(
    val id: Long,
    val name: String,
    private val unUsedCouponBook: CouponBook
) {
    fun useCoupon(coupon: Coupon) {
        unUsedCouponBook.deleteCoupon(coupon)
    }

    fun showMyCouponBook(): CouponBook {
        return unUsedCouponBook
    }

    fun receiveCoupon(coupon: Coupon) {
        unUsedCouponBook.addCoupon(coupon)
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
        return "Member(id=$id, name='$name', unUsedCouponBook=$unUsedCouponBook)"
    }
}