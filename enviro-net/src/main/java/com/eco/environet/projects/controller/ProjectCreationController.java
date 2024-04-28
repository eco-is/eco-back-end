package com.eco.environet.projects.controller;

import com.eco.environet.projects.dto.*;
import com.eco.environet.projects.service.ProjectCreationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
@Tag(name = "Project Creation", description = "Create projects and add documentation")
public class ProjectCreationController {

    private final ProjectCreationService projectCreationService;

    @Operation(summary = "Create project with basic info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created project with basic info", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping
    @PreAuthorize("hasRole('PROJECT_MANAGER') and #projectDto.managerId == authentication.principal.id")
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectCreationDto projectDto) throws IOException {
        ProjectDto result = projectCreationService.create(projectDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Update project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated project", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "text/plain"))
    })
    @PutMapping("/{projectId}")
    @PreAuthorize("hasRole('PROJECT_MANAGER') and @projectRepository.findById(#projectId).orElse(null)?.manager?.id == authentication.principal.id")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long projectId, @Valid @RequestBody ProjectUpdateDto updateDto) {
        ProjectDto result = projectCreationService.update(projectId, updateDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Delete project draft or archive ongoing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project deleted or archived", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{projectId}")
    @PreAuthorize("hasRole('PROJECT_MANAGER') and @projectRepository.findById(#projectId).orElse(null)?.manager?.id == authentication.principal.id")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectCreationService.delete(projectId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Add project document")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Document added", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping(value = "/{projectId}/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('PROJECT_MANAGER') and @projectRepository.findById(#projectId).orElse(null)?.manager?.id == authentication.principal.id")
    public ResponseEntity<DocumentDto> uploadDocument(
            @PathVariable Long projectId,
            @ModelAttribute @Valid DocumentCreationDto documentDto
    ) throws IOException {
        DocumentDto result = projectCreationService.uploadDocument(projectId, documentDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Delete project document")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Document deleted", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping(value = "/{projectId}/documents/{documentId}")
    @PreAuthorize("hasRole('PROJECT_MANAGER') and @projectRepository.findById(#projectId).orElse(null)?.manager?.id == authentication.principal.id")
    public ResponseEntity<Void> deleteDocument(
            @PathVariable Long projectId,
            @PathVariable Long documentId
    ) {
        projectCreationService.deleteDocument(projectId, documentId);
        return ResponseEntity.ok().build();
    }
}
