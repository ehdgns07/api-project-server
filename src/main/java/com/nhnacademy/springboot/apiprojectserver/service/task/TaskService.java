package com.nhnacademy.springboot.apiprojectserver.service.task;

import com.nhnacademy.springboot.apiprojectserver.domain.task.DeleteRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.EditTaskRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.NewTaskDto;
import com.nhnacademy.springboot.apiprojectserver.domain.task.NewTaskRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    Page<TaskDto> getPagedTask(Pageable pageable, Long projectId);

    NewTaskDto createTask(NewTaskRequest newTaskRequest);

    TaskDto modifyTask(EditTaskRequest editTaskRequest);

    TaskDto getTask(Long taskId);

    boolean removeTask(Long taskId, DeleteRequest deleteRequest);
}
