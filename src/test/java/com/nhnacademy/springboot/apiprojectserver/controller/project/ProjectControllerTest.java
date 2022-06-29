package com.nhnacademy.springboot.apiprojectserver.controller.project;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboot.apiprojectserver.config.ProjectStatus;
import com.nhnacademy.springboot.apiprojectserver.domain.project.ProjectRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
class ProjectControllerTest {

    Long projectId = 2L;
    @Autowired
    private MockMvc mvc;

    @Test
    void register() throws Exception{

        ProjectRequest projectRequest = ProjectRequest.builder()
            .projectName("testproject")
            .projectStatus(ProjectStatus.ENABLE.toString())
            .build();

        Long memberId = 1L;

        String requestBody = new ObjectMapper().writeValueAsString(projectRequest);

        this.mvc.perform(post("/projects/" + memberId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.projectName", equalTo("testproject")));
    }

    @Test
    void view() throws Exception{
        this.mvc.perform(get("/projects/{memberId}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].projectId", equalTo(1)));
    }

    @Test
    void editProject() throws Exception{
        ProjectRequest projectRequest = ProjectRequest.builder()
            .projectId(projectId)
            .projectName("updated project")
            .projectStatus("ENABLE")
            .build();

        String requestBody = new ObjectMapper().writeValueAsString(projectRequest);

        this.mvc.perform(put("/projects/{projectId}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.projectName",equalTo("updated project")));
    }

    @Test
    void eraseProject() throws Exception{
        this.mvc.perform(delete("/projects/1"))
            .andExpect(status().isOk());
    }
}