package com.nhnacademy.springboot.apiprojectserver.domain.milestone;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneDto {

    Long milestoneId;

    String milestoneName;

    LocalDate expectedBeginDt;

    LocalDate expectedCompleteDt;

    Long pkTaskId;
}
