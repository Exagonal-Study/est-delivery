# 1주차

## 이민의민족 - 쿠폰서비스

이스트소프트는 최근, 배달서비스를 런칭했습니다. 해당 서비스는 MSA로 나뉘어있고 이 중 쿠폰서버의 아키텍처를 구성해야 한다는 미션을 받았습니다.
팀원들은 각 요구사항을 토대로 도메인 설계를 진행하고 요구사항을 만족해야한다.

### 요구사항

1. 이민의 민족 쿠폰은 할인율 쿠폰과 금액 쿠폰 두종류가 존재합니다.
2. ID는 시간대별 순서가 보장되어야합니다.
3. 쿠폰발급의 이벤트는 버튼을 통한 발급, 정기적인 발급 총 두 개입니다.
4. 동일한 쿠폰은 하루에 하나씩만 발급 가능합니다.

## PR 방법

1. 해당 레포지토리를 fork합니다.
2. fork한 레포지토리를 clone합니다.
3. clone한 레포지토리에서 브랜치를 생성합니다. (브랜치 이름은 자유롭게)
4. 해당 브랜치에서 작업을 진행합니다.
5. 작업이 완료되면 PR을 보냅니다. (PR은 각 이름 브랜치로 보내주세요.)

## 설계 과정

### 요구사항 정리

- 쿠폰은 OOO 대상을 할인하기 위한 목적이다.
    - 가게에 진열된 상품 대상
- 회원은 OOO 행동으로 쿠폰을 발급받는다.
    - 가게에 접근해 쿠폰 발급 버튼을 누르는 행동
    - 가게 주인이 가게 쿠폰을 가져갔던 손님들에게 쿠폰을 뿌리는 행동
- 쿠폰의 할인 정책은 OOO 방법을 적용한다.
    - 고정금액을 할인하는 정액제 방법
    - 비율로 할인하는 정률제 방법

### 프로그래밍 요구사항 정리

- 발급받은 쿠폰 식별자는 시간대별로 순서가 보장되어야 한다.

> ID는 시간대별 순서가 보장되도록 고민한 이유는 쿠폰은 유효기간이 존재하고 제약 조건도 많아 사용자의 기억속에 휘발성이 많다고 생각해서이다. 그래서 시간 지역성을 띈다고 생각했고 한 번 조회할 때마다 하나의
> 블록을 읽어오는 InnonDB의 특성을 잘 살릴 수 있어보였다.

### 도메인 모델링

```mermaid
---
title: 이달의 민족
---
classDiagram
    Shop *-- UnusedCouponBook
    Shop *-- PublishedCouponBook
    Shop *-- HandOutCouponBook
    Shop *-- RoyalCustomers
    RoyalCustomers *-- Member
    Member *-- UnusedCouponBook
    CouponBook *-- Coupon
    FixDiscountCoupon --|> Coupon
    RateDiscountCoupon --|> Coupon
    ShopOwner *-- Shop
    UnusedCouponBook *-- CouponBook
    PublishedCouponBook *-- CouponBook
    HandOutCouponBook *-- CouponBook
    UsedCouponBook *-- CouponBook

    class CouponBook {
        -List~Coupon~ coupons
        +existCoupon(Coupon coupon) void
        +deleteCoupon(Coupon coupon) void
        +addCoupon(Coupon coupon) void
    }

    class UsedCouponBook {
        -CouponBook couponBook
        +addUsedCoupon(Coupon coupon) void
        +showUsedCoupons() List~Coupon~
    }

    class UnusedCouponBook {
        -CouponBook couponBook
        +addUnusedCoupon(Coupon coupon) void
        +removeUsedCoupon(Coupon coupon) void
        +showUnusedCoupons() List~Coupon~
    }

    class PublishedCouponBook {
        -CouponBook couponBook
        +addPublishedCoupon(Coupon coupon) void
        +removePublishedCoupon(Coupon coupon) void
        +showPublishedCoupons() List~Coupon~
    }

    class HandOutCouponBook {
        -CouponBook couponBook
        +addHandOutCoupon(Coupon coupon) void
        +showHandOutCoupons() List~Coupon~
    }

    class Coupon {
        -String name
        -String description
        -Enum couponType
        +isPublished() boolean
        +isHandOut() boolean
    }

    class FixDiscountCoupon {
        -Int discountAmount
    }

    class RateDiscountCoupon {
        -Int discountRate
    }

    class Member {
        -String name
        -CouponBook unUsedCouponBook
        +useCoupon(Coupon Coupon) void
        +showMyCouponBook() CouponBook
        +receiveCoupon(Coupon coupon) void
    }

    class ShopOwner {
        -Shop shop
        +handOutCouponToRoyalCustomersInShop(Coupon coupon) void
        +publishCouponInShop(Coupon coupon) void
        +addRoyalCustomerInShop(Member member...) void
        +showPublishedCouponsInShop() List~Coupon~
        +showHandOutCouponBookInShop() CouponBook
        +showRoyalCustomersInShop() List~Member~
    }

    class Shop {
        -String shopName
        -CouponBook publishedCoupons
        -CouponBook handOutCouponBook
        -CouponBook usedCouponBook
        -RoyalCustomers royalCustomers
        +publishCoupon(Coupon coupon) void
        +handOutCouponToRoyalCustomers(Coupon coupon) void
        +alreadyUsedCoupon(Coupon coupon) boolean
        +useCoupon(Coupon coupon) Coupon
        +addRoyalCustomer(Member member...) void
        +showPublishedCoupons() List~Coupon~
        +showHandOutCoupons() List~Coupon~
        +showRoyalCustomers() List~Member~
    }

    class RoyalCustomers {
        -List~Member~ members
        +handOutCoupon(Coupon coupon) void
        +addMember(Member member) void
        +showRoyalCustomers() List~Member~
    }

```

### 데이터 모델링

| 이름                  | 설명                                                     |
|---------------------|--------------------------------------------------------|
| coupon              | 가게 주인이 쿠폰을 발급한 정보.                                     |
| shop_owner          | 가게 주인 정보                                               |
| shop                | 가게 주인이 하나씩 소유하는 가게                                     |
| royal_member        | 가게의 단골 손님. 쿠폰을 한 번이라도 발급받는다면 이 가게의 단골!                 |
| publish_coupon_book | 가게 주인이 해당 가게에 게시한 쿠폰 정보 리스트                            |
| handout_coupon_book | 가게 주인이 해당 가게 단골에게 나눠준 쿠폰 정보 리스트                        |
| use_coupon_book     | 손님이 가게에서 한 번이라도 쿠폰을 사용했다면 가게에 다시 돌려주고, 그 기록을 남긴 정보 리스트 |
| member              | 회원의 정보                                                 |
| member_coupon_book  | 회원이 사용하지 않은 쿠폰 리스트                                     |

```mermaid
---
title: 이달의 민족
---

erDiagram
    royal_member {
        long shop_id
        long member_id
    }

    shop_owner {
long id pk
        string name
long shop_id
}

shop {
long id pk
string name
}

member {
long id pk
string name
}

publish_coupon_book {
long shop_id
long coupon_id
}

handout_coupon_book {
long shop_id
long coupon_id
}

use_coupon_book {
long member_id
long coupon_id
}

coupon {
long id pk
long shop_id
string name
string description
string type
int amount
}

member_coupon_book {
long member_id
long coupon_id
}

royal_member }o--|| member: contain
member_coupon_book }o--|| member: have
member_coupon_book }o--|| coupon: contains
handout_coupon_book }o--|| coupon: contains
publish_coupon_book }o--|| coupon: contains
use_coupon_book }o--|| coupon: contains
royal_member }o--|| shop: manage
handout_coupon_book }o--|| shop: have
publish_coupon_book }o--|| shop: have
shop_owner ||--|| shop: own
use_coupon_book }o--|| shop: have
```

## 유스 케이스 정리

### 회원은 게시된 쿠폰을 발행한다.

1. 회원 정보를 조회한다.
2. 쿠폰정보를 조회한다.
3. 가게가 가진 게시된 쿠폰 북에서 쿠폰을 발행한다.
4. 발급된 쿠폰을 사용자의 쿠폰북에 추가한다.
5. 가게 단골 손님으로 등록한다.

검증 목록

- 회원은 가게에 게시된 쿠폰북에서 쿠폰을 꺼내 자신의 쿠폰 북에 담는다.
- 이미 가진 쿠폰이라면 발행 할 수 없다.(도메인과 중복 검증)
- 게시되지 않은 쿠폰은 발행 될 수 없다.(도메인과 중복 검증)

### 가게 주인은 쿠폰을 가게에 게시한다.

1. 가게 주인 정보를 조회한다.
2. 게시할 쿠폰을 생성한다.
3. 가게 주인은 가게에다 게시된 쿠폰북에 쿠폰을 게시한다.

검증 목록

- 가게 주인은 가게에 쿠폰을 게시할 수 있다.
- 게시된 쿠폰북에 동일한 쿠폰이 있을 수 없다.(도메인과 중복 검증)

### 가게 주인은 단골들에게 쿠폰을 뿌린다.

1. 가게 주인 정보를 조회한다.
2. 아직 나눠준적 없는 쿠폰이라면 쿠폰을 생성해 나눠준 쿠폰북에 쿠폰을 추가한다.
3. 단골들 중 동일한 쿠폰을 아직 받지 않은 사용자에게 쿠폰을 뿌린다.

검증 목록

- 가게 주인은 가게에 쿠폰을 뿌릴 수 있다.
- 이미 나눠줬던 쿠폰을 다시 나눠줄 때 새로운 단골들에게만 나눠준다.

### 회원은 쿠폰을 사용한다.

1. 회원 정보를 조회한다.
2. 쿠폰 정보를 조회한다.
3. 회원이 가진 쿠폰북에서 쿠폰을 사용한다.
4. 가게에서 사용한 쿠폰을 받는다.

검증 목록

- 회원은 가게에 쿠폰을 사용할 수 있다.
- 이미 사용한 쿠폰은 사용할 수 없다.(도메인과 중복 검증)
- 게시되지 않거나 나눠주지 않은 쿠폰은 사용할 수 없다.(도메인과 중복 검증)
