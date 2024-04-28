package com.eco.environet.projects.service;

import com.eco.environet.projects.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ProjectCreationService {

    ProjectDto create(ProjectCreationDto projectDto);
    ProjectDto update(Long projectId, ProjectUpdateDto updateDto);
    void delete(Long projectId);
    DocumentDto uploadDocument(Long projectId, DocumentCreationDto documentDto) throws IOException;
    void deleteDocument(Long projectId, Long documentId);
}
