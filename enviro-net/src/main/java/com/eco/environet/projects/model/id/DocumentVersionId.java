package com.eco.environet.projects.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentVersionId implements Serializable {
    Long projectId;
    Long documentId;
    Long version;
}
