package com.nhnacademy.springboot.apiprojectserver.util;

import com.nhnacademy.springboot.apiprojectserver.entity.ProjectMember;
import com.nhnacademy.springboot.apiprojectserver.repository.projectmember.ProjectMemberRepository;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMemberCheck {

    private static ProjectMemberRepository projectMemberRepository;

    @Autowired
    private ProjectMemberCheck(
        ProjectMemberRepository projectMemberRepository) {
        ProjectMemberCheck.projectMemberRepository = projectMemberRepository;
    }

    public static boolean isMemberOfProject(Long memberId, ProjectMember.Id id) {
        ProjectMember projectMember = projectMemberRepository.findById(id).orElse(null);
        if(Objects.isNull(projectMember)){
            return false;
        }

        return Objects.equals(projectMember.getMember().getMemberId(), memberId);
    }
}
