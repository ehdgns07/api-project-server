package com.nhnacademy.springboot.apiprojectserver.repository.projectmember;

import com.nhnacademy.springboot.apiprojectserver.entity.ProjectMember;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Id>,
    CustomProjectMemberRepository{

    Collection<Object> findByIdMemberId(Long memberId);
}
