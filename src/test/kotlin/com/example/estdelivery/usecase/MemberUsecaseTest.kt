package com.example.estdelivery.usecase

import com.example.estdelivery.application.dto.member.RegisterMemberCommand
import com.example.estdelivery.application.port.out.MemberPersistencePort
import com.example.estdelivery.application.service.MemberService
import com.example.estdelivery.domain.member.Member
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.assertAll
import java.time.LocalDateTime

@DisplayName("멤버 유스케이스는")
class MemberUsecaseTest : BehaviorSpec({

    lateinit var memberService: MemberService
    lateinit var memberPersistencePort: MemberPersistencePort

    beforeTest {
        memberPersistencePort = mockk()

        memberService = withContext(Dispatchers.IO) {
            MemberService(memberPersistencePort)
        }
    }

    Given("회원가입 정보가 주어지고") {
        val email = "test@estsoft.com"
        val registerMemberCommand = RegisterMemberCommand(email)
        val saveMockMember = Member(1L, email, LocalDateTime.now())

        When("회원가입을 요청하면") {
            every { memberPersistencePort.insertMemberOrUpdate(any()) } returns saveMockMember
            val registerMember = memberService.registerMember(registerMemberCommand)

            Then("회원가입이 완료된다") {
                assertAll(
                    { registerMember.id shouldBe 1L },
                    { registerMember.email shouldBe email },
                    { registerMember.createdDateTime shouldBe saveMockMember.createdAt }
                )
            }
        }
    }

    Given("회원 아이디가 주어지고") {
        val memberId = 1L
        val saveMockMember = Member(1L, "test@estsoft.com", LocalDateTime.now())

        When("회원정보를 조회하면") {
            every { memberPersistencePort.findMemberById(any()) } returns saveMockMember
            val member = memberService.getMemberById(memberId)

            Then("회원정보가 조회된다") {
                assertAll(
                    { member.id shouldBe 1L },
                    { member.email shouldBe saveMockMember.email },
                    { member.createdDateTime shouldBe saveMockMember.createdAt }
                )
            }
        }
    }
})
