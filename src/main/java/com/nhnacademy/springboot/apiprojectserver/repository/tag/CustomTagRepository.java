package com.nhnacademy.springboot.apiprojectserver.repository.tag;

import com.nhnacademy.springboot.apiprojectserver.entity.Tag;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomTagRepository {

    List<Tag> findAllByTaskId(Long taskId);
}
