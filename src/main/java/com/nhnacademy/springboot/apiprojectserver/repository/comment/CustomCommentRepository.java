package com.nhnacademy.springboot.apiprojectserver.repository.comment;

import com.nhnacademy.springboot.apiprojectserver.entity.Comment;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomCommentRepository {

    List<Comment> findAllByTaskId(Long TaskId);
}
