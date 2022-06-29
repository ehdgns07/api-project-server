package com.nhnacademy.springboot.apiprojectserver.controller.milestone;

import com.nhnacademy.springboot.apiprojectserver.domain.milestone.MilestoneDto;
import com.nhnacademy.springboot.apiprojectserver.domain.milestone.MilestoneRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.AttachMilestoneRequest;
import com.nhnacademy.springboot.apiprojectserver.service.milestone.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MilestoneDto registerMilestone(@RequestBody MilestoneRequest milestoneRequest) {

        return milestoneService.createMilestone(milestoneRequest);
    }

    @PutMapping("/{milestoneId}")
    public MilestoneDto editMilestone(@RequestBody MilestoneRequest milestoneRequest,
                                      @PathVariable Long milestoneId) {

        return milestoneService.modifyMilestone(milestoneRequest, milestoneId);
    }

    @DeleteMapping("/{milestoneId}")
    public String eraseMilestoneId(@PathVariable Long milestoneId) {

        if (!milestoneService.remove(milestoneId)) {
            return "{\"messge\": \"마일스톤이 존재하지 않습니다.\"}";
        }

        return "{\"messge\": \"" + milestoneId + "번 마일스톤이 삭제되었습니다.\"}";
    }

    @PostMapping("/addMilestone")
    public MilestoneDto attachMilestone(@RequestBody AttachMilestoneRequest attachMilestoneRequest){

        return milestoneService.addMilestone(attachMilestoneRequest);

    }
}
