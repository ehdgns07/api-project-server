package com.nhnacademy.springboot.apiprojectserver.service.project;

import com.nhnacademy.springboot.apiprojectserver.config.ProjectStatus;
import com.nhnacademy.springboot.apiprojectserver.domain.project.ProjectDto;
import com.nhnacademy.springboot.apiprojectserver.domain.project.ProjectRequest;
import com.nhnacademy.springboot.apiprojectserver.entity.Member;
import com.nhnacademy.springboot.apiprojectserver.entity.Project;
import com.nhnacademy.springboot.apiprojectserver.entity.ProjectMember;
import com.nhnacademy.springboot.apiprojectserver.repository.member.MemberRepository;
import com.nhnacademy.springboot.apiprojectserver.repository.project.ProjectRepository;
import com.nhnacademy.springboot.apiprojectserver.repository.projectmember.ProjectMemberRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProjectDto createProject(ProjectRequest projectRequest, Long memberId) {
        List<Project> projectList =
            projectRepository.findByProjectName(projectRequest.getProjectName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if (projectList.size() == 0) {
            Project newProject = new Project();
            newProject.setProjectName(projectRequest.getProjectName());
            newProject.setProjectStatus(ProjectStatus.ENABLE.toString());
            newProject.setProjectCreatedDt(LocalDate.now());

            ProjectMember.Id id = new ProjectMember.Id(newProject.getProjectId(), memberId);
            ProjectMember projectMember = new ProjectMember();
            projectMember.setId(id);
            projectMember.setMember(member);

            if (projectMemberRepository.findById(id).isEmpty()) {

                projectMember.setIsAdmin("Y");
                newProject.addProjectMember(projectMember);
            }

            return modelMapper.map(projectRepository.save(newProject), ProjectRequest.class);
        }

        return null;
    }

    @Override
    public List<ProjectDto> readProjects(Long memberId,
                                         HttpServletRequest request) {

        if (projectMemberRepository.findByIdMemberId(memberId).isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> projectIds = projectMemberRepository.findAllByMemberId(memberId);
        List<Project> projects = projectRepository.findAllById(projectIds);
        List<ProjectDto> projectDtoList = new ArrayList<>();

        for (Project project : projects) {
            projectDtoList.add(modelMapper.map(project, ProjectRequest.class));
        }

        return projectDtoList;
    }

    @Override
    public ProjectDto modifyProject(Long projectId, ProjectRequest projectRequest,
                                    HttpServletRequest request) {

        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isEmpty()) {
            return null;
        }

        Project project = optionalProject.orElse(null);

        if(Objects.nonNull(project)) {
            project.setProjectName(projectRequest.getProjectName());
            project.setProjectStatus(projectRequest.getProjectStatus());
            return modelMapper.map(projectRepository.save(project), ProjectRequest.class);
        }

        return null;
    }

    @Override
    public boolean removeProject(Long projectId) {

        if (projectRepository.findById(projectId).isEmpty()) {
            return false;
        }

        projectRepository.deleteById(projectId);
        return true;
    }


}
