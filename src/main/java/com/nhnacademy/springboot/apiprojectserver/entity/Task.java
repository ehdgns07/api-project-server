package com.nhnacademy.springboot.apiprojectserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_name")
    private String taskName;


    @Column(name = "task_content")
    private String taskContent;

    @Column(name = "task_created_dt")
    private LocalDateTime taskCreatedDt;

    @Column(name = "task_expected_complete_dt")
    private LocalDateTime expectedCompleteDt;

    @Column(name = "task_completed_dt")
    private LocalDateTime completeDt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "member_id"),
        @JoinColumn(name = "project_id")
    })
    @JsonBackReference
    private ProjectMember projectMember;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    public void addTag(Tag tag){
        tag.setTask(this);
        tags.add(tag);
    }

    public void addComment(Comment comment){
        comment.setTask(this);
        comments.add(comment);
    }

}
