package com.example.estdelivery.application.service

import com.example.estdelivery.application.dto.member.MemberResponse
import com.example.estdelivery.application.dto.member.RegisterMemberCommand
import com.example.estdelivery.application.port.`in`.member.MemberReadUsecase
import com.example.estdelivery.application.port.`in`.member.MemberWriteUsecase
import com.example.estdelivery.application.port.out.MemberPersistencePort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberPersistencePort: MemberPersistencePort
) : MemberWriteUsecase, MemberReadUsecase {

    @Transactional
    override fun registerMember(registerMemberCommand: RegisterMemberCommand): MemberResponse {
        val member = registerMemberCommand.toDomain()
        val saveMember = memberPersistencePort.insertMemberOrUpdate(member)
        return MemberResponse(saveMember.id!!, saveMember.email, saveMember.createdAt!!)
    }

    @Transactional
    override fun getMemberById(memberId: Long): MemberResponse {
        val member = memberPersistencePort.findMemberById(memberId)
        return MemberResponse(member.id!!, member.email, member.createdAt!!)
    }

}
