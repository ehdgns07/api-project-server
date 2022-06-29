package com.nhnacademy.springboot.apiprojectserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @EmbeddedId
   private Pk pk;

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

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {

        @Column(name = "task_id")
        private Long taskId;

        @Column(name = "task_name")
        private String taskName;

    }
}
