package com.nhnacademy.springboot.apiprojectserver.controller.member;

import com.nhnacademy.springboot.apiprojectserver.domain.member.AddMemberDto;
import com.nhnacademy.springboot.apiprojectserver.domain.member.MemberDto;
import com.nhnacademy.springboot.apiprojectserver.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MemberDto registerMember(@RequestBody AddMemberDto addMemberDto){
        return memberService.addMember(addMemberDto);
    }
}
