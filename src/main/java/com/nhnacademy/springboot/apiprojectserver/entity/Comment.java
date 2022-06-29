package com.nhnacademy.springboot.apiprojectserver.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commentId;

    @Column(name="comment_content")
    String commentContent;

    @Column(name = "comment_created_dt")
    LocalDateTime createdDt;

    @Column(name = "comment_modified_dt")
    LocalDateTime modifiedDt;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "task_id"),
        @JoinColumn(name = "task_name")
    })
    Task task;

    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;
}
