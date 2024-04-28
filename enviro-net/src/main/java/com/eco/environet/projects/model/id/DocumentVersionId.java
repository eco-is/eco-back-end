package com.eco.environet.projects.model.id;

import lombok.Data;

import java.io.Serializable;

@Data
public class DocumentVersionId implements Serializable {
    Long projectId;
    Long documentId;
    Long version;
}
