package com.eco.environet.projects.service.impl;

import com.eco.environet.projects.dto.*;
import com.eco.environet.projects.model.*;
import com.eco.environet.projects.model.id.DocumentId;
import com.eco.environet.projects.model.id.DocumentVersionId;
import com.eco.environet.projects.repository.DocumentRepository;
import com.eco.environet.projects.repository.DocumentVersionRepository;
import com.eco.environet.projects.repository.ProjectRepository;
import com.eco.environet.projects.service.ProjectCreationService;
import com.eco.environet.users.model.User;
import com.eco.environet.users.repository.UserRepository;
import com.eco.environet.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Transactional
@Service
public class ProjectCreationServiceImpl implements ProjectCreationService {

    @Value("${projectFilePath}")
    private String projectFilePath;
    private final ProjectRepository projectRepository;
    private final DocumentRepository documentRepository;
    private final DocumentVersionRepository documentVersionRepository;
    private final UserRepository userRepository;

    @Override
    public ProjectDto create(ProjectCreationDto projectDto) {
        User manager = userRepository.findById(projectDto.getManagerId())
                .orElseThrow(() -> new EntityNotFoundException("Manager not found"));

        Project project = Mapper.map(projectDto, Project.class);
        project.setStatus(Status.DRAFT);
        project.setManager(manager);

        Project savedProject = projectRepository.save(project);

        if (savedProject.getType() == Type.INTERNAL) {
            addTemplates(savedProject);
        }

        return Mapper.map(savedProject, ProjectDto.class);
    }

    @Override
    public ProjectDto update(Long projectId, ProjectUpdateDto updateDto) {
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        if (existingProject.getStatus() == Status.REJECTED || existingProject.getStatus() == Status.ARCHIVED) {
            throw new IllegalStateException("Cannot update project with status: " + existingProject.getStatus());
        }

        existingProject.setName(updateDto.getName());
        existingProject.setDescription(updateDto.getDescription());
        existingProject.setDurationMonths(updateDto.getDurationMonths());
        existingProject.setBudget(updateDto.getBudget());

        Project updatedProject = projectRepository.save(existingProject);
        return Mapper.map(updatedProject, ProjectDto.class);
    }

    @Override
    public void delete(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        if (project.getStatus() == Status.DRAFT) {
            projectRepository.delete(project);
        } else  if (project.getStatus() == Status.ONGOING){
            project.setStatus(Status.ARCHIVED);
            projectRepository.save(project);
        } else {
            throw new IllegalStateException("Cannot delete or archive a project with status: " + project.getStatus());
        }
    }

    public DocumentDto uploadDocument(Long projectId, DocumentCreationDto documentDto) throws IOException {
        if (projectId == null || documentDto == null || documentDto.getFile() == null || documentDto.getFile().isEmpty()) {
            throw new IllegalArgumentException("Invalid parameters for document upload");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        Path filePath = saveFile(documentDto.getName(), documentDto.getFile(), project.getName());
        Document savedDocument = createDocument(projectId, documentDto.getName());
        createDocumentVersion(project, filePath, savedDocument);

        return  Mapper.map(savedDocument, DocumentDto.class);
    }

    @Override
    public void deleteDocument(Long projectId, Long documentId) {
        DocumentId id = new DocumentId(projectId, documentId);

        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));

        List<DocumentVersion> documentVersions = documentVersionRepository.findByDocumentId(documentId);
        for (DocumentVersion version : documentVersions) {
            deleteDocumentVersionFromFileSystem(version.getFilePath());
        }

        documentVersionRepository.deleteAll(documentVersions);
        documentRepository.delete(document);
    }

    private Document createDocument(Long projectId, String documentName) {
        Document document = new Document();
        document.setProjectId(projectId);
        document.setName(documentName);
        document.setProgress(new DocumentProgress());
        return documentRepository.save(document);
    }

    private void createDocumentVersion(Project project, Path filePath, Document savedDocument) {
        DocumentVersion documentVersion = new DocumentVersion();
        documentVersion.setVersion(0L);
        documentVersion.setDocumentId(savedDocument.getDocumentId());
        documentVersion.setProjectId(savedDocument.getProjectId());
        documentVersion.setFilePath(filePath.toString());
        documentVersion.setAuthor(project.getManager());
        documentVersionRepository.save(documentVersion);
    }

    private void addTemplates(Project savedProject) {
        Path templatesDir = Paths.get(projectFilePath, "Templates");
        try (Stream<Path> paths = Files.list(templatesDir)) {
            paths.forEach(filePath -> {
                String fileName = filePath.getFileName().toString();
                String documentName = constructDocumentName(fileName);
                try {
                    Path projectFolder = createProjectFolder(savedProject.getName());
                    Path destFilePath = Paths.get(projectFolder.toString(), fileName);
                    try {
                        Files.copy(filePath, destFilePath, StandardCopyOption.REPLACE_EXISTING);
                        Document savedDocument = createDocument(savedProject.getId(), documentName);
                        createDocumentVersion(savedProject, destFilePath, savedDocument);
                    } catch (IOException e) {
                        throw new RuntimeException("Error copying template file: " + fileName, e);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Error listing template files", e);
        }
    }

    private String constructDocumentName(String fileName) {
        String nameWithoutExtension = fileName.replaceAll("\\.[^.]*$", "");

        String[] parts = nameWithoutExtension.split("_");
        StringBuilder formattedFileName = new StringBuilder();
        for (String part : parts) {
            if (formattedFileName.length() > 0) {
                formattedFileName.append(" ");
            }
            formattedFileName.append(part.substring(0, 1).toUpperCase())
                    .append(part.substring(1).toLowerCase());
        }
        return formattedFileName.toString();
    }

    private Path saveFile(String documentName, MultipartFile file, String projectName) throws IOException {
        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String extension = StringUtils.getFilenameExtension(originalFilename);
        String filename = documentName.replaceAll("\\s+", "_").toLowerCase() + "_v0." + extension;

        Path projectFolder = createProjectFolder(projectName);

        Path filePath = Paths.get(projectFolder.toString(), filename);
        Files.copy(file.getInputStream(), filePath);
        return filePath;
    }

    private Path createProjectFolder(String projectName) throws IOException {
        Path projectFolder = Paths.get(projectFilePath + File.separator + projectName);
        if (!Files.exists(projectFolder)) {
            Files.createDirectories(projectFolder);
        }
        return projectFolder;
    }

    private void deleteDocumentVersionFromFileSystem(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
