package com.nhnacademy.springboot.apiprojectserver.service.milestone;

import com.nhnacademy.springboot.apiprojectserver.domain.milestone.MilestoneDto;
import com.nhnacademy.springboot.apiprojectserver.domain.milestone.MilestoneRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.AttachMilestoneRequest;

public interface MilestoneService {
    MilestoneDto createMilestone(MilestoneRequest milestoneRequest);

    MilestoneDto modifyMilestone(MilestoneRequest milestoneRequest, Long milestoneId);

    boolean remove(Long milestoneId);

    MilestoneDto addMilestone(AttachMilestoneRequest attachMilestoneRequest);
}
