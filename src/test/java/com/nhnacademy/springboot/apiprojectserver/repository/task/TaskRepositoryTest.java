package com.nhnacademy.springboot.apiprojectserver.repository.task;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springboot.apiprojectserver.config.RootConfig;
import com.nhnacademy.springboot.apiprojectserver.entity.Task;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
@Import(RootConfig.class)
class TaskRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    TaskRepository taskRepository;

    @Test
    void findByPk() {
        Task.Pk pk = new Task.Pk(1L, "new task test");

        Task task = Task.builder()
                        .pk(pk)
                        .taskContent("new task content")
                        .taskCreatedDt(LocalDateTime.now())
                        .build();

        testEntityManager.persist(task);

        Task findTask = taskRepository.findByPk(pk);

        assertThat(findTask.getPk()).isEqualTo(pk);
    }

    @Test
    void findByPkTaskId() {

        Task.Pk pk = new Task.Pk(1L, "new task test");

        Task task = Task.builder()
                        .pk(pk)
                        .taskContent("new task content")
                        .taskCreatedDt(LocalDateTime.now())
                        .build();

        testEntityManager.persist(task);

        Task findTask = taskRepository.findByPkTaskId(pk.getTaskId());

        assertThat(findTask.getPk()).isEqualTo(pk);

    }

    @Test
    void deleteByPkTaskId() {
        Task.Pk pk = new Task.Pk(1L, "new task test");

        Task task = Task.builder()
                        .pk(pk)
                        .taskContent("new task content")
                        .taskCreatedDt(LocalDateTime.now())
                        .build();

        testEntityManager.persist(task);

        taskRepository.deleteByPkTaskId(pk.getTaskId());

        assertThat(taskRepository.findByPkTaskId(pk.getTaskId())).isNull();
    }

    @Test
    void findAllByProjectMemberIdProjectId() {

        Long projectId = 1L;

        for(long i = 1L; i < 6L ;i++) {

        Task.Pk pk = new Task.Pk(i, "new task test");

        Task task = Task.builder()
                        .pk(pk)
                        .taskContent("new task content")
                        .taskCreatedDt(LocalDateTime.now())
                        .build();

        testEntityManager.persist(task);

        }

        Pageable pageable = PageRequest.of(0,5);

        Page<Task> pagedTask = taskRepository.findAllByProjectMemberIdProjectId(pageable,projectId);

        assertThat(pagedTask.getTotalElements()).isEqualTo(0);
    }
}