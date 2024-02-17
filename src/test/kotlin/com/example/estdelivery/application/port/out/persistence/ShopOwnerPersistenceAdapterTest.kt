package com.example.estdelivery.application.port.out.persistence

import com.example.estdelivery.application.port.out.persistence.mapper.fromShop
import com.example.estdelivery.application.port.out.persistence.mapper.fromShopOwner
import com.example.estdelivery.application.port.out.persistence.mapper.toShopOwner
import com.example.estdelivery.application.port.out.persistence.repository.ShopOwnerRepository
import com.example.estdelivery.application.port.out.persistence.repository.ShopRepository
import com.example.estdelivery.domain.fixture.게시할_쿠폰
import com.example.estdelivery.domain.fixture.새로_창업해서_아무것도_없는_프리퍼
import com.example.estdelivery.domain.fixture.이건창
import com.example.estdelivery.domain.fixture.일건창
import com.example.estdelivery.domain.shop.ShopOwner
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.exactly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.NoSuchElementException
import java.util.Optional

class ShopOwnerPersistenceAdapterTest : FreeSpec({

    lateinit var shopOwnerRepository: ShopOwnerRepository
    lateinit var shopRepository: ShopRepository
    lateinit var shopOwnerPersistenceAdapter: ShopOwnerPersistenceAdapter

    beforeTest {
        shopOwnerRepository = mockk<ShopOwnerRepository>()
        shopRepository = mockk<ShopRepository>()
        shopOwnerPersistenceAdapter = ShopOwnerPersistenceAdapter(shopOwnerRepository, shopRepository)
    }

    "findById" - {
        "가게 주인을 찾는다." {
            // given
            val id = 1L
            val shopOwnerEntity = fromShopOwner(ShopOwner(새로_창업해서_아무것도_없는_프리퍼(), id))
            // when
            every { shopOwnerRepository.findById(id) } returns Optional.of(shopOwnerEntity)
            val result = shopOwnerPersistenceAdapter.findById(id)
            // then
            result shouldBe toShopOwner(shopOwnerEntity)
        }

        "가게 주인이 없으면 예외가 발생한다." {
            // given
            val id = 1L
            // when
            every { shopOwnerRepository.findById(id) } returns Optional.empty()
            // then
            shouldThrow<NoSuchElementException> {
                shopOwnerPersistenceAdapter.findById(id)
            }
        }
    }

    "findByShopId" - {
        "가게 식별자를 이용해 가게 주인 정보를 찾는다." {
            // given
            val shopId = 1L
            val shop = 새로_창업해서_아무것도_없는_프리퍼()
            val shopEntity = fromShop(shop)
            val shopOwnerEntity = fromShopOwner(ShopOwner(shop, 1L))

            // when
            every { shopRepository.findById(shopId) } returns Optional.of(shopEntity)
            every { shopOwnerRepository.findByShopEntity(shopEntity) } returns Optional.of(shopOwnerEntity)
            val result = shopOwnerPersistenceAdapter.findByShopId(shopId)

            // then
            result shouldBe toShopOwner(shopOwnerEntity)
        }

        "가게 식별자로 가게를 찾지 못하면 예외가 발생한다." {
            // given
            val shopId = 1L
            // when
            every { shopRepository.findById(shopId) } returns Optional.empty()
            // then
            shouldThrow<NoSuchElementException> {
                shopOwnerPersistenceAdapter.findByShopId(shopId)
            }
        }

        "가게는 찾았지만 가게 주인을 찾지 못하면 예외가 발생한다." {
            // given
            val shopId = 1L
            val shop = 새로_창업해서_아무것도_없는_프리퍼()
            val shopEntity = fromShop(shop)

            // when
            every { shopRepository.findById(shopId) } returns Optional.of(shopEntity)
            every { shopOwnerRepository.findByShopEntity(shopEntity) } returns Optional.empty()

            // then
            shouldThrow<NoSuchElementException> {
                shopOwnerPersistenceAdapter.findByShopId(shopId)
            }
        }
    }

    "update" - {
        "가게 주인 정보를 업데이트한다." {
            // given
            val shopOwner = ShopOwner(새로_창업해서_아무것도_없는_프리퍼(), 1L)
            // when
            shopOwner.publishCouponInShop(게시할_쿠폰)
            shopOwner.issuePublishedCouponInShop(게시할_쿠폰)
            일건창().receiveCoupon(게시할_쿠폰)
            이건창().receiveCoupon(게시할_쿠폰)
            shopOwner.addRoyalCustomersInShop(일건창(), 이건창())
            every { shopOwnerRepository.save(any()) } returns fromShopOwner(shopOwner)
            shopOwnerPersistenceAdapter.update(shopOwner)

            // then
            verify(exactly = 1) { shopOwnerRepository.save(any()) }
        }
    }


})
