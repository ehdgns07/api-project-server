package com.nhnacademy.springboot.apiprojectserver.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMemberDto implements MemberDto{

    Long memberId;

    String username;

    String email;

}
