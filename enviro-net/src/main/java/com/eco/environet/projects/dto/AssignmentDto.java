package com.eco.environet.projects.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AssignmentDto {

    @NotBlank(message = "Document is required")
    private Long documentId;

    @NotBlank(message = "At least one reviewer is required")
    private List<Long> reviewerIds;

    @NotBlank(message = "At least one writer is required")
    private List<Long> writerIds;
}
