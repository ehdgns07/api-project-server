package com.nhnacademy.springboot.apiprojectserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMemberRequest{

    private Long projectId;

    private Long adminId;

    private Long newMemberId;
}
