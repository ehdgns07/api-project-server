package com.nhnacademy.springboot.apiprojectserver.domain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditTaskRequest {

    private Long projectId;

    private Long memberId;

    private Long taskId;

    private String taskContent;
}
