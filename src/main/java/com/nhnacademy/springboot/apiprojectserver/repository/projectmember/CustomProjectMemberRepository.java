package com.nhnacademy.springboot.apiprojectserver.repository.projectmember;

import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomProjectMemberRepository {
    List<Long> findAllByMemberId(Long memberId);
}
