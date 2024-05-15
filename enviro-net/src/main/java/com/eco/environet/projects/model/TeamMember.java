package com.eco.environet.projects.model;

import com.eco.environet.users.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "team_members", schema = "projects")
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public static TeamMember initalizeTeamMember(Long projectId, Long userId) {
        TeamMember teamMember = new TeamMember();

        Project project = new Project();
        project.setId(projectId);

        User user = new User();
        user.setId(userId);

        teamMember.setProject(project);
        teamMember.setUser(user);
        return teamMember;
    }
}