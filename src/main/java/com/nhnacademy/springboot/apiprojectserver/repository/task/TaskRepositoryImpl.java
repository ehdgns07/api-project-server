package com.nhnacademy.springboot.apiprojectserver.repository.task;

import com.nhnacademy.springboot.apiprojectserver.entity.QTask;
import com.nhnacademy.springboot.apiprojectserver.entity.Task;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class TaskRepositoryImpl extends QuerydslRepositorySupport implements CustomTaskRepository
{

    private final JPAQueryFactory queryFactory;

    public TaskRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Task.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Long updateTask(Task newTask) {
        QTask task = QTask.task;

        return queryFactory.update(task)
                                .where(task.pk.taskId.eq(newTask.getPk().getTaskId()))
                                .set(task.pk.taskName, newTask.getPk().getTaskName())
                                .set(task.taskContent, newTask.getTaskContent())
                                .execute();
    }
}
