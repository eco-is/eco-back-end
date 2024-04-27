package com.eco.environet.projects.service.impl;

import com.eco.environet.projects.dto.ProjectCreationDto;
import com.eco.environet.projects.dto.ProjectDto;
import com.eco.environet.projects.dto.ProjectUpdateDto;
import com.eco.environet.projects.model.Project;
import com.eco.environet.projects.model.Status;
import com.eco.environet.projects.repository.ProjectRepository;
import com.eco.environet.projects.service.ProjectCreationService;
import com.eco.environet.users.model.User;
import com.eco.environet.users.repository.UserRepository;
import com.eco.environet.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ProjectCreationServiceImpl implements ProjectCreationService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    @Override
    public ProjectDto create(ProjectCreationDto projectDto) {
        User manager = userRepository.findById(projectDto.getManagerId())
                .orElseThrow(() -> new EntityNotFoundException("Manager not found"));

        Project project =  Mapper.map(projectDto, Project.class);
        project.setStatus(Status.DRAFT);
        project.setManager(manager);

        Project savedProject = projectRepository.save(project);

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
}
