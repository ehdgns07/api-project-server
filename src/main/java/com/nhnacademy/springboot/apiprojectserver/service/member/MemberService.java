package com.nhnacademy.springboot.apiprojectserver.service.member;

import com.nhnacademy.springboot.apiprojectserver.domain.member.AddMemberDto;
import com.nhnacademy.springboot.apiprojectserver.domain.member.MemberDto;

public interface MemberService {

    MemberDto addMember(AddMemberDto addMemberDto);

}
