package com.eco.environet.finance.repository;

import com.eco.environet.finance.model.ProjectRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRevenueRepository extends JpaRepository<ProjectRevenue, Long> {
}
