package com.eco.environet.projects.dto;

import com.eco.environet.projects.model.DocumentProgress;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Data
public class DocumentReviewDto {

    private Long version;
    private Timestamp reviewDate;
    private TeamMemberDto reviewer;
    private String comment;
    private Boolean isApproved;
}
