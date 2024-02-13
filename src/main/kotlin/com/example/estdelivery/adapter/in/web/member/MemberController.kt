package com.example.estdelivery.adapter.`in`.web.member

import com.example.estdelivery.adapter.`in`.web.dto.BaseResponseDto
import com.example.estdelivery.application.dto.member.MemberResponse
import com.example.estdelivery.application.dto.member.RegisterMemberCommand
import com.example.estdelivery.application.port.`in`.member.MemberReadUsecase
import com.example.estdelivery.application.port.`in`.member.MemberWriteUsecase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class MemberController(
    private val memberWriteUsecase: MemberWriteUsecase,
    private val memberReadUsecase: MemberReadUsecase
) {

    @PostMapping("/member/register")
    fun registerMember(@RequestBody registerMemberCommand: RegisterMemberCommand): BaseResponseDto<MemberResponse> {
        val registerMember = memberWriteUsecase.registerMember(registerMemberCommand)
        return BaseResponseDto.success(data = registerMember)
    }

    @GetMapping("/member/{memberId}")
    fun getMemberById(@PathVariable memberId: Long): BaseResponseDto<MemberResponse> {
        val member = memberReadUsecase.getMemberById(memberId)
        return BaseResponseDto.success(data = member)
    }

}
