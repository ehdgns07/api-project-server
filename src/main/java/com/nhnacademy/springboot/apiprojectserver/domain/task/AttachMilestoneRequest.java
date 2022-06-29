package com.nhnacademy.springboot.apiprojectserver.domain.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachMilestoneRequest{

    Long pkTaskId;

    Long milestoneId;

}
