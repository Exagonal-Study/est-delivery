package com.example.estdelivery.domain.member

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import org.junit.jupiter.api.Assertions.*

@DisplayName("회원 도메인은")
class MemberTest : BehaviorSpec({

    Given("이메일이 주어지고") {
        val email = "test@estsoft.com"

        When("회원을 생성하면") {
            val member = Member(email = email)

            Then("회원이 생성된다") {
                assertAll(
                    { assertNotNull(member) },
                    { assertEquals(email, member.email) }
                )
            }
        }
    }

    Given("만약 빈 이메일이거나 형식이 올바르지 않은 이메일이 주어지고") {
        val emptyEmail = ""
        val invalidEmail = "test"

        When("회원을 생성하면") {
            val exception1 = assertThrows(IllegalArgumentException::class.java) {
                Member(email = emptyEmail)
            }
            val exception2 = assertThrows(IllegalArgumentException::class.java) {
                Member(email = invalidEmail)
            }

            Then("에러가 발생한다.") {
                assertAll(
                    { assertEquals("이메일이 입력되지 않았습니다.", exception1.message) },
                    { assertEquals("이메일 형식이 올바르지 않습니다.", exception2.message) }
                )
            }
        }
    }
})
