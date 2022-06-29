package com.nhnacademy.springboot.apiprojectserver.service.milestone;

import com.nhnacademy.springboot.apiprojectserver.domain.milestone.MilestoneDto;
import com.nhnacademy.springboot.apiprojectserver.domain.milestone.MilestoneRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.task.AttachMilestoneRequest;
import com.nhnacademy.springboot.apiprojectserver.entity.Milestone;
import com.nhnacademy.springboot.apiprojectserver.entity.Project;
import com.nhnacademy.springboot.apiprojectserver.entity.Task;
import com.nhnacademy.springboot.apiprojectserver.repository.milestone.MilestoneRepository;
import com.nhnacademy.springboot.apiprojectserver.repository.project.ProjectRepository;
import com.nhnacademy.springboot.apiprojectserver.repository.task.TaskRepository;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    @Override
    public MilestoneDto createMilestone(MilestoneRequest milestoneRequest) {

        Milestone milestone = Milestone.builder()
            .milestoneName(milestoneRequest.getMilestoneName())
            .expectedBeginDt(milestoneRequest.getExpectedBeginDt())
            .expectedCompleteDt(milestoneRequest.getExpectedCompleteDt())
            .build();

        Optional<Project> optionalProject =
            projectRepository.findById(milestoneRequest.getProjectId());
        Project project = optionalProject.orElse(null);
        if(Objects.nonNull(project)) {
            project.addMilestone(milestone);
        }
        return modelMapper.map(milestoneRepository.save(milestone), MilestoneDto.class);
    }

    @Override
    public MilestoneDto modifyMilestone(MilestoneRequest milestoneRequest, Long milestoneId) {

        Optional<Milestone> optionalMilestone = milestoneRepository.findById(milestoneId);
        Milestone milestone = optionalMilestone.orElse(null);

        if (Objects.nonNull(milestone)) {
            milestone.setMilestoneName(milestoneRequest.getMilestoneName());
            milestone.setExpectedBeginDt(milestoneRequest.getExpectedBeginDt());
            milestone.setExpectedCompleteDt(milestoneRequest.getExpectedCompleteDt());

            return modelMapper.map(milestoneRepository.save(milestone), MilestoneDto.class);
        }

        return null;
    }

    @Override
    public boolean remove(Long milestoneId) {

        if(milestoneRepository.findById(milestoneId).isPresent()){
            milestoneRepository.deleteById(milestoneId);
            return true;
        }
        return false;
    }

    @Override
    public MilestoneDto addMilestone(AttachMilestoneRequest attachMilestoneRequest) {

        Optional<Milestone> optionalMilestone = milestoneRepository.findById(attachMilestoneRequest.getMilestoneId());
        Milestone milestone = optionalMilestone.orElse(null);

        Task task = taskRepository.findByPkTaskId(attachMilestoneRequest.getPkTaskId());

        if (Objects.isNull(milestone) || Objects.isNull(task)){
            return null;
        }

        milestone.setTask(task);

        return modelMapper.map(milestoneRepository.save(milestone), MilestoneDto.class);
    }
}
