package com.eco.environet.projects.repository;

import com.eco.environet.projects.model.Review;
import com.eco.environet.projects.model.ReviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.request.documentVersion.projectId = :projectId AND r.request.documentVersion.documentId = :documentId")
    List<Review> findAllByDocument(@Param("projectId") Long projectId, @Param("documentId") Long documentId);

    List<Review> findByRequestId(Long requestId);
}
