package com.example.estdelivery.application.port.out.persistence

import com.example.estdelivery.application.port.out.persistence.mapper.fromCoupon
import com.example.estdelivery.application.port.out.persistence.repository.CouponRepository
import com.example.estdelivery.domain.fixture.게시된_고정_할인_쿠폰
import com.example.estdelivery.domain.fixture.게시할_쿠폰
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.Optional

class CouponPersistenceAdapterTest : FreeSpec({
    var couponRepository = mockk<CouponRepository>()
    var couponPersistenceAdapter = CouponPersistenceAdapter(couponRepository)

    beforeTest {
        couponRepository = mockk<CouponRepository>()
        couponPersistenceAdapter = CouponPersistenceAdapter(couponRepository)
    }

    "create" - {
        "쿠폰을 생성한다." {
            // given
            val coupon = 게시할_쿠폰

            // when
            every { couponRepository.save(any()) } returns fromCoupon(게시된_고정_할인_쿠폰)
            val createdCoupon = couponPersistenceAdapter.create(coupon)

            // then
            createdCoupon shouldBe 게시된_고정_할인_쿠폰
        }
    }

    "exists" - {
        "쿠폰이 존재하는지 확인한다." {
            // when
            every { couponRepository.existsById(게시된_고정_할인_쿠폰.id!!) } returns true
            val exists = couponPersistenceAdapter.exists(게시된_고정_할인_쿠폰.id!!)

            // then
            exists shouldBe true
        }
    }

    "findById" - {
        "쿠폰을 조회한다." {
            // given
            val couponEntity = fromCoupon(게시된_고정_할인_쿠폰)

            // when
            every { couponRepository.findById(couponEntity.id!!) } returns Optional.of(couponEntity)
            val foundCoupon = couponPersistenceAdapter.findById(couponEntity.id!!)

            // then
            foundCoupon shouldBe 게시된_고정_할인_쿠폰
        }

        "존재하지 않는 쿠폰인 경우" {
            // when
            every { couponRepository.findById(2) } returns Optional.empty()

            // then
            shouldThrow<IllegalArgumentException> {
                couponPersistenceAdapter.findById(2)
            }.message shouldBe "존재하지 않는 쿠폰입니다."
        }
    }
})
