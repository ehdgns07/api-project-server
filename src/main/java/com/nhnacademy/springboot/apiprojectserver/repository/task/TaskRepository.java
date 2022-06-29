package com.nhnacademy.springboot.apiprojectserver.repository.task;

import com.nhnacademy.springboot.apiprojectserver.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>, CustomTaskRepository {

    Task findByPk(Task.Pk pk);

    Task findByPkTaskId(Long taskId);

    void deleteByPkTaskId(Long taskId);

    Page<Task> findAllByProjectMemberIdProjectId(Pageable pageable, Long projectId);

    void deleteByPk(Task.Pk pk);
}
