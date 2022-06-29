package com.nhnacademy.springboot.apiprojectserver.domain.task;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long pkTaskId;

    private String pkTaskName;

    private String taskContent;

    private LocalDateTime taskCreatedDt;

}
