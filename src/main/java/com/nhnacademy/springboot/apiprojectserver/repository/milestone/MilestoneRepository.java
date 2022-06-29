package com.nhnacademy.springboot.apiprojectserver.repository.milestone;

import com.nhnacademy.springboot.apiprojectserver.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>{
}
