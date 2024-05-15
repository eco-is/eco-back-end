package com.eco.environet.projects.service.impl;

import com.eco.environet.projects.dto.AssignmentDto;
import com.eco.environet.projects.dto.DocumentDto;
import com.eco.environet.projects.dto.TeamMemberCreationDto;
import com.eco.environet.projects.dto.TeamMemberDto;
import com.eco.environet.projects.model.*;
import com.eco.environet.projects.repository.AssignmentRepository;
import com.eco.environet.projects.repository.DocumentRepository;
import com.eco.environet.projects.repository.ProjectRepository;
import com.eco.environet.projects.repository.TeamMemberRepository;
import com.eco.environet.projects.service.TeamService;
import com.eco.environet.users.model.User;
import com.eco.environet.users.repository.UserRepository;
import com.eco.environet.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamMemberRepository teamMemberRepository;
    private final DocumentRepository documentRepository;
    private final ProjectRepository projectRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

    @Override
    public List<TeamMemberDto> findAvailableMembers(Long projectId) {
        List<User> availableMembers = filterTeamMembers(projectId);

        return availableMembers.stream()
                .map(user -> {
                    TeamMemberDto dto = new TeamMemberDto();
                    dto.setUserId(user.getId());
                    dto.setFirstName(user.getName());
                    dto.setLastName(user.getSurname());
                    dto.setEmail(user.getEmail());
                    dto.setRole(user.getRole().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TeamMemberDto> findTeamMembers(Long projectId) {
        List<TeamMember> teamMembers = teamMemberRepository.findByProjectId(projectId);

        return teamMembers.stream()
                .map(this::createTeamMemberDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addTeamMember(TeamMemberCreationDto teamMemberCreationDto) {
        Long projectId = teamMemberCreationDto.getProjectId();
        Long userId = teamMemberCreationDto.getUserId();
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Organization Member not found"));

        teamMemberRepository.save(TeamMember.initalizeTeamMember(projectId, userId));
    }

    @Override
    public void removeTeamMember(Long memberId) {
        TeamMember teamMember = teamMemberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Team Member not found"));

        teamMemberRepository.delete(teamMember);
    }

    @Override
    public DocumentDto assignTeamMembers(Long projectId, AssignmentDto assignmentDto) {
        Document document = documentRepository.findByDocumentIdAndProjectId(assignmentDto.getDocumentId(), projectId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));

        List<TeamMember> writers = teamMemberRepository.findAllById(assignmentDto.getWriterIds());
        List<TeamMember> reviewers = teamMemberRepository.findAllById(assignmentDto.getReviewerIds());
        validateTeamMembers(reviewers, writers, projectId);

        assign(document, writers, reviewers);

        DocumentDto documentDto = Mapper.map(document, DocumentDto.class);
        documentDto.setWriters(Mapper.mapList(writers, TeamMemberDto.class));
        documentDto.setReviewers(Mapper.mapList(reviewers, TeamMemberDto.class));

        return documentDto;
    }

    private void assign(Document document, List<TeamMember> writers, List<TeamMember> reviewers) {
        List<Assignment> currentAssignments = assignmentRepository.findByDocument(
                document.getDocumentId(), document.getProjectId());

        List<Assignment> writerAssignments = writers.stream()
                .map(writer -> createAssignment(document, writer, Task.WRITE))
                .toList();

        List<Assignment> reviewerAssignments = reviewers.stream()
                .map(reviewer -> createAssignment(document, reviewer, Task.REVIEW))
                .toList();

        assignmentRepository.deleteAll(currentAssignments);
        assignmentRepository.saveAll(reviewerAssignments);
        assignmentRepository.saveAll(writerAssignments);
    }

    private List<User> filterTeamMembers(Long projectId) {
        List<User> organizationMembers = userRepository.findAllOrganizationMembers();
        List<TeamMember> teamMembers = teamMemberRepository.findByProjectId(projectId);

        List<User> teamUsers = teamMembers.stream()
                .map(TeamMember::getUser)
                .toList();

        return organizationMembers.stream()
                .filter(user -> !teamUsers.contains(user))
                .toList();
    }

    private TeamMemberDto createTeamMemberDto(TeamMember teamMember) {
        TeamMemberDto teamMemberDto = new TeamMemberDto();
        teamMemberDto.setId(teamMember.getId());
        teamMemberDto.setProjectId(teamMember.getProject().getId());
        teamMemberDto.setUserId(teamMember.getUser().getId());
        teamMemberDto.setFirstName(teamMember.getUser().getName());
        teamMemberDto.setLastName(teamMember.getUser().getSurname());
        teamMemberDto.setEmail(teamMember.getUser().getEmail());
        teamMemberDto.setRole(teamMember.getUser().getRole().toString());

        return teamMemberDto;
    }

    private void validateTeamMembers(List<TeamMember> reviewers, List<TeamMember> writers, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        reviewers.forEach(reviewer -> {
            if (!reviewer.getProject().equals(project)) {
                throw new IllegalArgumentException("Reviewer is not part of the project team");
            }
        });

        writers.forEach(writer -> {
            if (!writer.getProject().equals(project)) {
                throw new IllegalArgumentException("Writer is not part of the project team");
            }
        });
    }

    private Assignment createAssignment(Document document, TeamMember teamMember, Task task) {
        Assignment assignment = new Assignment();
        assignment.setDocument(document);
        assignment.setTeamMember(teamMember);
        assignment.setTask(task);
        return assignment;
    }
}
