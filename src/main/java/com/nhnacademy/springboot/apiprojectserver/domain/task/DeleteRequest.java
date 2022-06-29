package com.nhnacademy.springboot.apiprojectserver.domain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRequest {

    private Long projectId;
    private Long memberId;
    private String taskName;
}
