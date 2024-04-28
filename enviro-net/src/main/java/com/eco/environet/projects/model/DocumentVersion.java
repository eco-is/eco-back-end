package com.eco.environet.projects.model;

import com.eco.environet.projects.model.id.DocumentVersionId;
import com.eco.environet.users.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(DocumentVersionId.class)
@Table(name = "document_versions")
public class DocumentVersion {

    @Id
    private Long version;

    @Id
    private Long documentId;

    @Id
    private Long projectId;

    @Column(nullable = false)
    private String filePath;

    @ManyToOne
    private User author;
}
