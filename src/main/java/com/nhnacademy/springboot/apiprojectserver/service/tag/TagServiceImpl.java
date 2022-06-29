package com.nhnacademy.springboot.apiprojectserver.service.tag;

import com.nhnacademy.springboot.apiprojectserver.domain.tag.AttachTagRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.tag.TagRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.tag.TagDto;
import com.nhnacademy.springboot.apiprojectserver.entity.Project;
import com.nhnacademy.springboot.apiprojectserver.entity.Tag;
import com.nhnacademy.springboot.apiprojectserver.entity.Task;
import com.nhnacademy.springboot.apiprojectserver.repository.project.ProjectRepository;
import com.nhnacademy.springboot.apiprojectserver.repository.tag.TagRepository;
import com.nhnacademy.springboot.apiprojectserver.repository.task.TaskRepository;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    @Override
    public TagDto createTag(TagRequest tagRequest) {
        Tag tag = Tag.builder()
            .tagName(tagRequest.getTagName()).build();

        Optional<Project> optionalProject = projectRepository.findById(tagRequest.getProjectId());
        Project project = optionalProject.orElse(null);

        if(Objects.nonNull(project)) {
            project.addTag(tag);
        }

        Long taskId = tagRequest.getTaskId();

        if(taskId != null) {
            Task task = taskRepository.findByPkTaskId(tagRequest.getTaskId());

            task.addTag(tag);
        }
        return modelMapper.map(tagRepository.save(tag), TagDto.class);
    }

    @Override
    public TagDto modify(TagRequest tagRequest, Long tagId) {

        Optional<Tag> optionalTag = tagRepository.findById(tagId);

        Tag tag = optionalTag.orElse(null);

        if(Objects.isNull(tag)){
            return null;
        }

        tag.setTagName(tagRequest.getTagName());

        return modelMapper.map(tagRepository.save(tag), TagDto.class);
    }

    @Override
    public boolean removeTag(Long tagId) {

        if(tagRepository.findById(tagId).isEmpty()){
            return false;
        }

        tagRepository.deleteById(tagId);

        return true;
    }

    @Override
    public TagDto addTag(AttachTagRequest attachTagRequest) {

        Tag tag = tagRepository.findById(attachTagRequest.getTagId()).orElse(null);

        Task task = taskRepository.findByPkTaskId(attachTagRequest.getPkTaskId());

        if(Objects.isNull(tag) || Objects.isNull(task)){
            return null;
        }

        task.addTag(tag);

        return modelMapper.map(tagRepository.save(tag), TagDto.class);
    }
}
