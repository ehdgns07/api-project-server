package com.nhnacademy.springboot.apiprojectserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @Column(name = "member_id")
    Long memberId;

    String username;

    String email;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProjectMember> projectMembers = new ArrayList<>();

    public void addProjectMember(ProjectMember projectMember){
        projectMember.setMember(this);
        projectMembers.add(projectMember);
    }
}
