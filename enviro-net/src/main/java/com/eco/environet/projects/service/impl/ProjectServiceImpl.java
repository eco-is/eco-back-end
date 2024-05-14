package com.eco.environet.projects.service.impl;

import com.eco.environet.projects.dto.*;
import com.eco.environet.projects.model.*;
import com.eco.environet.projects.repository.DocumentRepository;
import com.eco.environet.projects.repository.ProjectRepository;
import com.eco.environet.projects.repository.ProjectSpecifications;
import com.eco.environet.projects.service.ProjectService;
import com.eco.environet.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final DocumentRepository documentRepository;

    @Override
    public Page<ProjectDto> findAllProjects(String name, Pageable pageable) {
        Specification<Project> spec = Specification.where(org.apache.commons.lang3.StringUtils.isBlank(name) ?
                null : ProjectSpecifications.nameLike(name));
        Page<Project> projects = projectRepository.findAll(spec, pageable);

        return Mapper.mapPage(projects, ProjectDto.class);
    }

    @Override
    public List<DocumentDto> getDocuments(long projectId) {
       List<Document> documents = documentRepository.findByProjectId(projectId);

        return Mapper.mapList(documents, DocumentDto.class);
    }
}
