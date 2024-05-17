package com.eco.environet.projects.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "assignments", schema = "projects")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "document_id", referencedColumnName = "document_id"),
            @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    })
    private Document document;

    @ManyToOne
    @JoinColumn(name = "team_member_id", referencedColumnName = "id")
    private TeamMember teamMember;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Task task;
}
