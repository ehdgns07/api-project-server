package com.nhnacademy.springboot.apiprojectserver.controller.project;

import com.nhnacademy.springboot.apiprojectserver.domain.project.ProjectDto;
import com.nhnacademy.springboot.apiprojectserver.domain.project.ProjectRequest;
import com.nhnacademy.springboot.apiprojectserver.service.project.ProjectService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
@Slf4j
public class ProjectController {
    private final ProjectService projectService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{memberId}")
    public ProjectDto register(@RequestBody ProjectRequest projectRequest, @PathVariable Long memberId) {
        return projectService.createProject(projectRequest, memberId);
    }

    @GetMapping("/{memberId}")
    public List<ProjectDto> view( @PathVariable Long memberId,
                                 HttpServletRequest httpServletRequest) {
        return projectService.readProjects(memberId, httpServletRequest);
    }

    @PutMapping("/{projectId}")
    public ProjectDto editProject(@RequestBody ProjectRequest projectRequest,
                                  @PathVariable Long projectId, HttpServletRequest request){

        return projectService.modifyProject(projectId, projectRequest, request);
    }

    @DeleteMapping("/{projectId}")
    public String eraseProject(@PathVariable Long projectId) {

        if (!projectService.removeProject(projectId)) {
            return "{\"messge\": \"프로젝트가 존재하지 않습니다.\"}";
        }
        return "{\"messge\": \"" + projectId + "번 프로젝트가 삭제되었습니다.\"}";
    }
}
