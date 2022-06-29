package com.nhnacademy.springboot.apiprojectserver.domain.comment;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditCommentDto implements CommentDto{

    Long commentId;

    String commentContent;

    Long memberId;

    LocalDateTime modifiedDt;

}
