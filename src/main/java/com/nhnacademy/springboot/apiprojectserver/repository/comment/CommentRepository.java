package com.nhnacademy.springboot.apiprojectserver.repository.comment;

import com.nhnacademy.springboot.apiprojectserver.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CustomCommentRepository {
}
