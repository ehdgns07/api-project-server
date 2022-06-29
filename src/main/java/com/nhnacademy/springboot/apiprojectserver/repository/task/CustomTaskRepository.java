package com.nhnacademy.springboot.apiprojectserver.repository.task;

import com.nhnacademy.springboot.apiprojectserver.entity.Task;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomTaskRepository {

    Long updateTask(Task newTask);

}
