package com.nhnacademy.springboot.apiprojectserver.controller.task;

import com.nhnacademy.springboot.apiprojectserver.domain.task.DeleteRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.EditTaskRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.NewTaskDto;
import com.nhnacademy.springboot.apiprojectserver.domain.task.NewTaskRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.TaskDto;
import com.nhnacademy.springboot.apiprojectserver.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/page/{projectId}")
    public Page<TaskDto> pagedTask(Pageable pageable, @PathVariable Long projectId){
        return taskService.getPagedTask(pageable, projectId);

    }

    @GetMapping("/{taskId}")
    public TaskDto taskContent(@PathVariable Long taskId){
        return taskService.getTask(taskId);
    }

    @PostMapping
    public NewTaskDto newTask(@RequestBody NewTaskRequest newTaskRequest){

        return taskService.createTask(newTaskRequest);
    }

    @PutMapping
    public TaskDto editTask(@RequestBody EditTaskRequest editTaskRequest){
        return taskService.modifyTask(editTaskRequest);
    }

    @DeleteMapping("/{taskId}")
    public String eraseTask(@PathVariable Long taskId, @RequestBody DeleteRequest deleteRequest){

        if(!taskService.removeTask(taskId, deleteRequest)){
            return "{\"messge\": \"업무가 존재하지 않습니다.\"}";
        }
        return "{\"messge\": \"" + taskId + "번 업무가 삭제되었습니다.\"}";
    }

}
