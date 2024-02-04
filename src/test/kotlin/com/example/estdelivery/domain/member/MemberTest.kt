package com.example.estdelivery.domain.member

import com.example.estdelivery.domain.coupon.Coupon
import com.example.estdelivery.domain.coupon.CouponBook
import com.example.estdelivery.domain.coupon.CouponType
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MemberTest : FreeSpec({
    lateinit var member: Member
    val coupon = Coupon.FixDiscountCoupon(1, 1000, "1000원 할인 쿠폰", "1000원 할인 쿠폰 설명", CouponType.IS_HAND_OUT)

    beforeTest {
        member = Member("홍길동", CouponBook())
    }

    "쿠폰을 추가할 수 있다." {
        member.receiveCoupon(coupon)
        member.showMyCouponBook().showCoupons().contains(coupon) shouldBe true
    }

    "쿠폰을 사용할 수 있다." {
        member.receiveCoupon(coupon)
        member.useCoupon(coupon)
        member.showMyCouponBook().showCoupons().contains(coupon) shouldBe false
    }
})
