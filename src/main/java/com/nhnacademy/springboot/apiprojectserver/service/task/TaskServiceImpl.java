package com.nhnacademy.springboot.apiprojectserver.service.task;

import static com.nhnacademy.springboot.apiprojectserver.util.ProjectMemberCheck.isMemberOfProject;

import com.nhnacademy.springboot.apiprojectserver.domain.task.DeleteRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.EditTaskRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.NewTaskDto;
import com.nhnacademy.springboot.apiprojectserver.domain.task.NewTaskRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.TaskDto;
import com.nhnacademy.springboot.apiprojectserver.entity.ProjectMember;
import com.nhnacademy.springboot.apiprojectserver.entity.Task;
import com.nhnacademy.springboot.apiprojectserver.repository.projectmember.ProjectMemberRepository;
import com.nhnacademy.springboot.apiprojectserver.repository.task.TaskRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public Page<TaskDto> getPagedTask(Pageable pageable, Long projectId) {

        return taskRepository.findAllByProjectMemberIdProjectId(pageable, projectId).map(
            task -> TaskDto.builder()
                .taskId(task.getTaskId())
                .taskName(task.getTaskName())
                .taskCreatedDt(task.getTaskCreatedDt())
                .build());
    }

    @Override
    public TaskDto getTask(Long taskId) {

        return modelMapper.map(taskRepository.findByPkTaskId(taskId), TaskDto.class);
    }


    @Override
    @Transactional
    public NewTaskDto createTask(NewTaskRequest newTaskRequest) {

        ProjectMember.Id id =
            new ProjectMember.Id(newTaskRequest.getMemberId(), newTaskRequest.getProjectId());

        Task task = Task.builder()
            .taskName(newTaskRequest.getTaskName())
            .taskContent(newTaskRequest.getTaskContent())
            .taskCreatedDt(LocalDateTime.now())
            .build();

        ProjectMember projectMember = projectMemberRepository.findById(id).orElse(null);

        if (Objects.nonNull(projectMember)) {

            projectMember.addTask(task);
        }
        Long taskCount = taskRepository.count();

        return modelMapper.map(taskRepository.save(task), NewTaskDto.class);

    }


    @Override
    @Transactional
    public TaskDto modifyTask(EditTaskRequest editTaskRequest) {

        ProjectMember.Id id = new ProjectMember.Id(editTaskRequest.getMemberId(),
            editTaskRequest.getProjectId());

        Task task = taskRepository.findByPkTaskId(editTaskRequest.getTaskId());

        if (isMemberOfProject(editTaskRequest.getMemberId(), id) && Objects.nonNull(task)) {

            task.setTaskContent(editTaskRequest.getTaskContent());
            return modelMapper.map(taskRepository.save(task), TaskDto.class);
        }

        return null;
    }

    @Transactional
    @Override
    public boolean removeTask(Long taskId, DeleteRequest deleteRequest) {

        ProjectMember.Id id = new ProjectMember.Id(deleteRequest.getMemberId(),
            deleteRequest.getProjectId());

        if (isMemberOfProject(deleteRequest.getMemberId(), id)) {
            if (Objects.isNull(taskRepository.findById(taskId))) {
                return false;
            }

            taskRepository.deleteById(taskId);

            return true;
        }

        return false;
    }
}
