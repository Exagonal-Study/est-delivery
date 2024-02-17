package com.example.estdelivery.application.port.out.persistence.mapper

import com.example.estdelivery.application.port.out.persistence.entity.MemberEntity
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.member.Member
import com.example.estdelivery.domain.member.UnusedCouponBook

internal fun fromMember(member: Member): MemberEntity {
    return MemberEntity(
        member.name,
        member.showMyCouponBook().map { fromCoupon(it) },
        member.id
    )
}

internal fun toMember(entity: MemberEntity): Member {
    return Member(
        entity.id!!,
        entity.name,
        UnusedCouponBook(
            CouponBook(entity.unusedCoupons.map { toCoupon(it) })
        )
    )
}
