package com.nhnacademy.springboot.apiprojectserver.service.projectMember;

import com.nhnacademy.springboot.apiprojectserver.domain.NewMemberDto;
import com.nhnacademy.springboot.apiprojectserver.domain.NewMemberRequest;
import com.nhnacademy.springboot.apiprojectserver.entity.ProjectMember;
import com.nhnacademy.springboot.apiprojectserver.repository.projectmember.ProjectMemberRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService{
    private final ProjectMemberRepository projectMemberRepository;
    private final ModelMapper modelMapper;

    @Override
    public NewMemberDto addNewMember(NewMemberRequest newMemberRequest) {

        ProjectMember.Id id = new ProjectMember.Id(newMemberRequest.getAdminId(),
            newMemberRequest.getProjectId());

        ProjectMember.Id newId = new ProjectMember.Id(newMemberRequest.getNewMemberId(),
            newMemberRequest.getProjectId());

        if(Objects.equals(projectMemberRepository.findById(id).orElseThrow().getIsAdmin(), "Y")){

            ProjectMember projectMember = ProjectMember.builder()
                .id(newId)
                .isAdmin("N")
                .build();

            return modelMapper.map(projectMemberRepository.save(projectMember), NewMemberDto.class);
        }

        return null;
    }
}
