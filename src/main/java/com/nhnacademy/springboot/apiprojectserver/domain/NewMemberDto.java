package com.nhnacademy.springboot.apiprojectserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMemberDto {

    private Long idProjectId;

    private Long idMemberId;

    private String isAdmin;
}
