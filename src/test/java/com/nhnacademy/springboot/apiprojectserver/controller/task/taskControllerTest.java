package com.nhnacademy.springboot.apiprojectserver.controller.task;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboot.apiprojectserver.domain.task.DeleteRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.EditTaskRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.NewTaskRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class taskControllerTest {

    Long taskId = 18L;

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    void testPagedTask() throws Exception{
        Long projectId = 1L;
        Pageable pageable = PageRequest.of(0,5);

        this.mvc.perform(get("/tasks/page/" + projectId)
            .param("page","0")
            .param("size", "5"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    void testNewTask() throws Exception{
        NewTaskRequest newTask = NewTaskRequest.builder()
            .taskName("new task1")
            .taskContent("new Content")
            .projectId(1L)
            .memberId(1L)
            .build();

        String requestBody = new ObjectMapper().writeValueAsString(newTask);

        this.mvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.pkTaskName", equalTo("new task1")));


    }

    @Test
    @Order(3)
    void taskContent() throws Exception{

        this.mvc.perform(get("/tasks/{taskId}", taskId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.pkTaskName", equalTo("new task1")));
    }

    @Test
    @Order(4)
    void editTask() throws Exception {
        EditTaskRequest editTaskRequest = new EditTaskRequest(1L,1L,taskId,"edited content");

        String requestBody = new ObjectMapper().writeValueAsString(editTaskRequest);

        this.mvc.perform(put("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.taskContent", equalTo("edited content")));
    }

    @Test
    @Order(5)
    void eraseTask() throws Exception{

        DeleteRequest deleteRequest = new DeleteRequest(1L, 1L, "new task1");

        String requestBody = new ObjectMapper().writeValueAsString(deleteRequest);

        this.mvc.perform(delete("/tasks/" + taskId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }
}