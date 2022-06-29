package com.nhnacademy.springboot.apiprojectserver.controller.projectmember;

import com.nhnacademy.springboot.apiprojectserver.domain.NewMemberDto;
import com.nhnacademy.springboot.apiprojectserver.domain.NewMemberRequest;
import com.nhnacademy.springboot.apiprojectserver.service.projectMember.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @PostMapping("/newMember")
    public NewMemberDto putInNewMember(@RequestBody NewMemberRequest newMemberRequest){
        return projectMemberService.addNewMember(newMemberRequest);
    }
}
