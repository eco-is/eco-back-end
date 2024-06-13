package com.eco.environet.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
//@Document(collection = "event")
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    private String id;
    private String transactionId;
    private Long documentId;
    private DocumentPayload payload;
    private String source;
    private LocalDateTime createdAt;
    private String status;
    private List<History> eventHistory = new ArrayList<>();
}
