package com.nhnacademy.springboot.apiprojectserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Milestone {
    @Id
    @Column(name = "milestone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long milestoneId;

    @Column(name = "milestone_name")
    private String milestoneName;

    @Column(name = "milestone_expected_begin_dt")
    private LocalDate expectedBeginDt;

    @Column(name = "milestone_expected_complete_dt")
    private LocalDate expectedCompleteDt;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "task_id"),
        @JoinColumn(name = "task_name")
    })
    private Task task;
}
