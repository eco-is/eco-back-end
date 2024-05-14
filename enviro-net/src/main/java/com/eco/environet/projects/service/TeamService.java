package com.eco.environet.projects.service;

import com.eco.environet.projects.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {

    List<TeamMemberDto> findAvailableMembers(Long projectId);
    List<TeamMemberDto> findTeamMembers(Long projectId);
    TeamMemberDto addTeamMember(TeamMemberCreationDto teamMemberDto);
    void removeTeamMember(Long memberId);
    DocumentDto assignTeamMembers(Long projectId, AssignmentDto assignmentDto);
}
