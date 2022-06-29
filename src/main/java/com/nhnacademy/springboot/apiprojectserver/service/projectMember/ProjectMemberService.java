package com.nhnacademy.springboot.apiprojectserver.service.projectMember;

import com.nhnacademy.springboot.apiprojectserver.domain.NewMemberDto;
import com.nhnacademy.springboot.apiprojectserver.domain.NewMemberRequest;

public interface ProjectMemberService {

    NewMemberDto addNewMember(NewMemberRequest newMemberRequest);
}
