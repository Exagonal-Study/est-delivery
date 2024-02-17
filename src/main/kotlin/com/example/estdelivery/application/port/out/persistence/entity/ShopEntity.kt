package com.example.estdelivery.application.port.out.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "shop")
class ShopEntity(
    @ManyToMany
    @JoinTable(
        name = "publish_coupon_book",
        joinColumns = [JoinColumn(name = "shop_id")],
        inverseJoinColumns = [JoinColumn(name = "coupon_id")]
    )
    val publishedCouponBook: List<CouponEntity>,

    @ManyToMany
    @JoinTable(
        name = "handout_coupon_book",
        joinColumns = [JoinColumn(name = "shop_id")],
        inverseJoinColumns = [JoinColumn(name = "coupon_id")]
    )
    var handOutCouponBook: List<CouponEntity>,

    @ManyToMany
    @JoinTable(
        name = "use_coupon_book",
        joinColumns = [JoinColumn(name = "shop_id")],
        inverseJoinColumns = [JoinColumn(name = "coupon_id")]
    )
    var usedCouponBook: List<CouponEntity>,

    @ManyToMany
    @JoinTable(
        name = "use_coupon_book",
        joinColumns = [JoinColumn(name = "shop_id")],
        inverseJoinColumns = [JoinColumn(name = "member_id")]
    )
    var royalCustomers: List<MemberEntity>,

    var name: String,
    @Id
    @Column(name = "shop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
}
