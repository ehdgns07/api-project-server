package com.nhnacademy.springboot.apiprojectserver.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteCommentDto {

    private Long commentId;

    private Long memberId;

}
