package com.eco.environet.projects.repository;

import com.eco.environet.projects.model.Document;
import com.eco.environet.projects.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    List<TeamMember> findByProjectId(Long projectId);
}
