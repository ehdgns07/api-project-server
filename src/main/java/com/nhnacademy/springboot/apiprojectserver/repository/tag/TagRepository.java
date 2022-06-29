package com.nhnacademy.springboot.apiprojectserver.repository.tag;

import com.nhnacademy.springboot.apiprojectserver.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>, CustomTagRepository {
    Tag findByTagName(String tagName);
}
