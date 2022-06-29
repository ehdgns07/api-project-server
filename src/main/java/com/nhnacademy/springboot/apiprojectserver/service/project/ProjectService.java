package com.nhnacademy.springboot.apiprojectserver.service.project;

import com.nhnacademy.springboot.apiprojectserver.domain.project.ProjectDto;
import com.nhnacademy.springboot.apiprojectserver.domain.project.ProjectRequest;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {
    ProjectDto createProject(ProjectRequest projectRequest, Long MemberId);

    List<ProjectDto> readProjects(Long memberId, HttpServletRequest request);

    ProjectDto modifyProject(Long projectId,
                             ProjectRequest projectRequest, HttpServletRequest request);

    boolean removeProject(Long projectId);

}
