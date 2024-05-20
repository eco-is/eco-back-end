package com.eco.environet.projects.repository;

import com.eco.environet.projects.model.Assignment;
import com.eco.environet.projects.model.Document;
import com.eco.environet.projects.model.id.AssignmentId;
import com.eco.environet.projects.model.id.DocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, AssignmentId> {

    @Query("SELECT a FROM Assignment a WHERE a.documentId = :documentId AND a.projectId = :projectId")
    List<Assignment> findByDocument(Long documentId, Long projectId);

    @Query("SELECT a FROM Assignment a WHERE a.userId = :userId")
    List<Assignment> findByUser(Long userId);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
            "FROM Assignment a " +
            "WHERE a.documentId = :documentId AND a.projectId = :projectId AND a.userId = :userId AND a.task = 'WRITE'")
    Boolean isWriter(Long documentId, Long projectId, Long userId);
}
