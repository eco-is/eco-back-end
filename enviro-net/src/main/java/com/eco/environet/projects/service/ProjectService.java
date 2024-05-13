package com.eco.environet.projects.service;

import com.eco.environet.projects.dto.*;
import com.eco.environet.users.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ProjectService {

    Page<ProjectDto> findAllProjects(String name, Pageable pageable);
    List<DocumentDto> getDocuments(long projectId);
}
