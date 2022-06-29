package com.nhnacademy.springboot.apiprojectserver.controller.tag;

import com.nhnacademy.springboot.apiprojectserver.domain.tag.AttachTagRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.tag.TagDto;
import com.nhnacademy.springboot.apiprojectserver.domain.tag.TagRequest;
import com.nhnacademy.springboot.apiprojectserver.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    @PostMapping
    public TagDto registerTag(@RequestBody TagRequest tagRequest) {
        return tagService.createTag(tagRequest);
    }

    @PutMapping("{tagId}")
    public TagDto editTag(@RequestBody TagRequest tagRequest, @PathVariable Long tagId) {
        return tagService.modify(tagRequest, tagId);
    }

    @DeleteMapping("/{tagId}")
    public String eraseTag(@PathVariable Long tagId) {

        if (!tagService.removeTag(tagId)) {
            return "{\"messge\": \"태그가 존재하지 않습니다.\"}";
        }

        return "{\"messge\": \"" + tagId + "번 태그가 삭제되었습니다.\"}";
    }

    @PostMapping("/addTag")
    public TagDto attachTag(@RequestBody AttachTagRequest attachTagRequest){
        return tagService.addTag(attachTagRequest);
    }

}

