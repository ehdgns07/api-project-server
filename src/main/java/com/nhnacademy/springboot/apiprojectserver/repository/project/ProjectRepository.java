package com.nhnacademy.springboot.apiprojectserver.repository.project;

import com.nhnacademy.springboot.apiprojectserver.entity.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByProjectName(String projectName);
}
