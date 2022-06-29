package com.nhnacademy.springboot.apiprojectserver.service.member;

import com.nhnacademy.springboot.apiprojectserver.domain.member.AddMemberDto;
import com.nhnacademy.springboot.apiprojectserver.domain.member.MemberDto;
import com.nhnacademy.springboot.apiprojectserver.entity.Member;
import com.nhnacademy.springboot.apiprojectserver.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    @Override
    public MemberDto addMember(AddMemberDto addMemberDto) {

        Member member = Member.builder()
            .memberId(addMemberDto.getMemberId())
            .username(addMemberDto.getUsername())
            .email(addMemberDto.getEmail()).build();

        return modelMapper.map(memberRepository.save(member), AddMemberDto.class);
    }
}
