package com.nhnacademy.springboot.apiprojectserver.repository.projectmember;

import com.nhnacademy.springboot.apiprojectserver.entity.ProjectMember;
import com.nhnacademy.springboot.apiprojectserver.entity.QProjectMember;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ProjectMemberRepositoryImpl extends QuerydslRepositorySupport implements CustomProjectMemberRepository{

    public ProjectMemberRepositoryImpl() {
        super(ProjectMember.class);
    }

    @Override
    public List<Long> findAllByMemberId(Long memberId){

        QProjectMember projectMember = QProjectMember.projectMember;

       return from(projectMember)
            .select(projectMember.project.projectId)
            .where(projectMember.member.memberId.eq(memberId))
            .fetch();
    }
}
