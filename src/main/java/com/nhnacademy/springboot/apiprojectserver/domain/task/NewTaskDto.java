package com.nhnacademy.springboot.apiprojectserver.domain.task;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTaskDto {

    private Long pkTaskId;

    private String pkTaskName;

    private String taskContent;

    private LocalDateTime taskCreatedDt;

}
