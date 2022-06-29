package com.nhnacademy.springboot.apiprojectserver.controller.milestone;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboot.apiprojectserver.domain.milestone.MilestoneRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.AttachMilestoneRequest;
import java.time.LocalDate;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MilestoneControllerTest {

    Long milestoneId = 2L;

    @Autowired
    MockMvc mvc;

    @Test
    @Order(1)
    void registerMilestone() throws Exception {
        MilestoneRequest milestoneRequest = MilestoneRequest.builder()
                                                            .milestoneName("name")
                                                            .projectId(2L)
                                                            .expectedBeginDt(LocalDate.now())
                                                            .build();

        String requestBody = new ObjectMapper().writeValueAsString(milestoneRequest);

        this.mvc.perform(post("/milestones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.milestoneName", equalTo("name")));

    }

    @Test
    @Order(2)
    void editMilestone() throws Exception{
        MilestoneRequest milestoneRequest = MilestoneRequest.builder()
            .milestoneName("name2")
            .expectedBeginDt(LocalDate.of(2022,6,15))
            .build();

        String requestBody = new ObjectMapper().writeValueAsString(milestoneRequest);

        this.mvc.perform(put("/milestones/{milestoneId}", milestoneId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.milestoneName", equalTo("name2")));
    }

    @Test
    @Order(4)
    void eraseMilestoneId() throws Exception{
        this.mvc.perform(delete("/milestones/{milestoneId}", milestoneId))
            .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void attachMilestone() throws Exception{
        AttachMilestoneRequest attachMilestoneRequest =
            AttachMilestoneRequest.builder()
                .pkTaskId(2L)
                .milestoneId(milestoneId).build();

        String requestBody = new ObjectMapper().writeValueAsString(attachMilestoneRequest);

        this.mvc.perform(post("/milestones/addMilestone")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.milestoneId", equalTo(milestoneId.intValue())))
            .andExpect(jsonPath("$.pkTaskId", equalTo(2)));
    }
}