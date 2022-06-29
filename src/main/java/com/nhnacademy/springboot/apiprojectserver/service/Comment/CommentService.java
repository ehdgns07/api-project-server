package com.nhnacademy.springboot.apiprojectserver.service.Comment;


import com.nhnacademy.springboot.apiprojectserver.domain.comment.CommentDto;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.DeleteCommentDto;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.NewCommentRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.EditCommentDto;

public interface CommentService {

    CommentDto createComment(NewCommentRequest newCommentRequest);

    CommentDto modifyComment(EditCommentDto editCommentDto);

    String removeComment(DeleteCommentDto deleteCommentDto);
}
