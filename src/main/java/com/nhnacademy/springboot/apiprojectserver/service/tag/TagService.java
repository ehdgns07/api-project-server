package com.nhnacademy.springboot.apiprojectserver.service.tag;

import com.nhnacademy.springboot.apiprojectserver.domain.tag.AttachTagRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.tag.TagRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.tag.TagDto;

public interface TagService {
    TagDto createTag(TagRequest tagRequest);

    TagDto modify(TagRequest tagRequest, Long tagId);

    boolean removeTag(Long tagId);

    TagDto addTag(AttachTagRequest attachTagRequest);
}
