package com.nhnacademy.springboot.apiprojectserver.domain.task;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewTaskRequest {

    private String taskName;

    private String taskContent;

    private Long projectId;

    private Long memberId;
}
