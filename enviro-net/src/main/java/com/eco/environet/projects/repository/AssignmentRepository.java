package com.eco.environet.projects.repository;

import com.eco.environet.projects.model.Assignment;
import com.eco.environet.projects.model.Document;
import com.eco.environet.projects.model.id.DocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

}
