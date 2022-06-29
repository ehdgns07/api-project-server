package com.nhnacademy.springboot.apiprojectserver.repository.member;

import com.nhnacademy.springboot.apiprojectserver.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
