package com.nhnacademy.springboot.apiprojectserver.repository.tag;

import com.nhnacademy.springboot.apiprojectserver.entity.QTag;
import com.nhnacademy.springboot.apiprojectserver.entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class TagRepositoryImpl extends QuerydslRepositorySupport implements CustomTagRepository {

    public TagRepositoryImpl() {
        super(Tag.class);
    }


    @Override
    public List<Tag> findAllByTaskId(Long taskId) {
        QTag tag = QTag.tag;

        return from(tag)
            .where(tag.task.pk.taskId.eq(taskId))
            .fetch();
    }
}
