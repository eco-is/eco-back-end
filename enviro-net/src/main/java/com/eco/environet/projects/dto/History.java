package com.eco.environet.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private String source;
    private String status;
    private String message;
    private LocalDateTime createdAt;

}
