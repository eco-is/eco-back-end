package com.eco.environet.projects.controller;

import com.eco.environet.projects.dto.*;
import com.eco.environet.projects.service.DocumentManagementService;
import com.eco.environet.projects.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects/assignments")
@Tag(name = "Assignment Management", description = "Manage documentation assignment")
public class AssignmentController {

    private final DocumentManagementService documentManagementService;

    @Operation(summary = "Get documents assigned to user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched assigned documents", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping
    @PreAuthorize("authentication.principal.role.isOrganizationMember() and #userId == authentication.principal.id")
    public ResponseEntity<List<DocumentTaskDto>> getAssignedDocuments(@RequestParam Long userId)
    {
        List<DocumentTaskDto> result = documentManagementService.getAssignedDocuments(userId);
        return ResponseEntity.ok(result);
    }
}