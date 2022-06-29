package com.nhnacademy.springboot.apiprojectserver.repository.project;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springboot.apiprojectserver.config.ProjectStatus;
import com.nhnacademy.springboot.apiprojectserver.config.RootConfig;
import com.nhnacademy.springboot.apiprojectserver.entity.Project;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(RootConfig.class)
class ProjectRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    void findByProjectName() {

        Project project = Project.builder()
            .projectName("testproject")
            .projectStatus(ProjectStatus.ENABLE.toString())
            .projectCreatedDt(LocalDate.now()).build();

        testEntityManager.persist(project);

        List<Project> projectList = projectRepository.findByProjectName("testproject");

        assertThat(projectList.get(0).getProjectName()).isEqualTo("testproject");

    }
}