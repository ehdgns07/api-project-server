package com.nhnacademy.springboot.apiprojectserver.controller.comment;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.DeleteCommentDto;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.EditCommentDto;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.NewCommentRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentControllerTest {

    Long commentId = 3L;

    @Autowired
    MockMvc mvc;

    @Test
    @Order(1)
    void registerComment() throws Exception{
        NewCommentRequest newCommentRequest = NewCommentRequest.builder()
            .commentContent("test comment")
            .memberId(1L)
            .pkTaskId(1L)
            .build();

        String requestBody = new ObjectMapper().writeValueAsString(newCommentRequest);

        this.mvc.perform(post("/comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.commentContent", equalTo("test comment")));

    }

    @Test
    @Order(2)
    void editComment() throws Exception{
        EditCommentDto editCommentDto = EditCommentDto.builder()
            .commentContent("edit comment")
            .commentId(commentId)
            .memberId(1L).build();

        String requestBody = new ObjectMapper().writeValueAsString(editCommentDto);

        this.mvc.perform(put("/comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.commentContent", equalTo(editCommentDto.getCommentContent())));
    }

    @Test
    @Order(3)
    void eraseComment() throws Exception{
        DeleteCommentDto deleteCommentDto = DeleteCommentDto.builder()
            .commentId(commentId)
            .memberId(1L).build();

        String requestBody = new ObjectMapper().writeValueAsString(deleteCommentDto);

        this.mvc.perform(delete("/comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk());

    }
}