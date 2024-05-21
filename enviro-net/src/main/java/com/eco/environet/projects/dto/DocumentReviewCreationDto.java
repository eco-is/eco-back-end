package com.eco.environet.projects.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DocumentReviewCreationDto {

    @NotNull(message = "User is required")
    private Long userId;

    @NotNull(message = "Approval is required")
    private Boolean isApproved;

    private String comment;
}
