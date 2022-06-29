package com.nhnacademy.springboot.apiprojectserver.domain.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest{

    Long tagId;

    String tagName;

    Long projectId;

    Long taskId;

}
