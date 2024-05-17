package com.eco.environet.finance.repository;

import com.eco.environet.finance.model.OrganizationGoal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationGoalRepository extends JpaRepository<OrganizationGoal, Long> {
    Page<OrganizationGoal> findByValidPeriodEndDateIsNull(Pageable pageable);
}
