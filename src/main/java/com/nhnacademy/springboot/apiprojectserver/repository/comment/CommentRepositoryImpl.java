package com.nhnacademy.springboot.apiprojectserver.repository.comment;

import com.nhnacademy.springboot.apiprojectserver.entity.Comment;
import com.nhnacademy.springboot.apiprojectserver.entity.QComment;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CustomCommentRepository {
    public CommentRepositoryImpl() {
        super(Comment.class);
    }

    @Override
    public List<Comment> findAllByTaskId(Long taskId) {

        QComment comment = QComment.comment;

        return from(comment)
            .where(comment.task.pk.taskId.eq(taskId))
            .fetch();
    }
}
