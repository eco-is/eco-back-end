package com.eco.environet.projects.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentPayload {
    private Long projectId;
    private Long documentId;
    private String name;
    private Long version;
    private String text;
}
