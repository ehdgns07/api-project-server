package com.nhnacademy.springboot.apiprojectserver.controller.comment;

import com.nhnacademy.springboot.apiprojectserver.domain.comment.CommentDto;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.DeleteCommentDto;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.NewCommentRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.EditCommentDto;
import com.nhnacademy.springboot.apiprojectserver.service.Comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommentDto registerComment(@RequestBody NewCommentRequest newCommentRequest){
        return commentService.createComment(newCommentRequest);
    }

    @PutMapping
    public CommentDto editComment(@RequestBody EditCommentDto editCommentDto){
        return commentService.modifyComment(editCommentDto);
    }

    @DeleteMapping
    public String eraseComment(@RequestBody DeleteCommentDto deleteCommentDto){
        return commentService.removeComment(deleteCommentDto);
    }

    @GetMapping("/test")
    public String commentTest(){
        return null;
    }
}
