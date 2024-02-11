package com.example.estdelivery.application.port.`in`

import com.example.estdelivery.application.port.`in`.command.IssuePublishedCouponCommand

interface IssuePublishedCouponUseCase {
    /**
     * 회원은 가게에 게시된 쿠폰을 발생한다. 이미 발행된 쿠폰은 재발행 할 수 없고, 게시되지 않은 쿠폰은 발행할 수 없다.
     * @param issuePublishedCouponCommand
     */
    fun issuePublishedCoupon(issuePublishedCouponCommand: IssuePublishedCouponCommand)
}