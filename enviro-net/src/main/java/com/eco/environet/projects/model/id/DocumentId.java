package com.eco.environet.projects.model.id;

import lombok.Data;

import java.io.Serializable;

@Data
public class DocumentId implements Serializable {

    private Long projectId;
    private Long documentId;
}