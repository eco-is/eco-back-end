package com.eco.environet.finance.services;

import com.eco.environet.finance.dto.OrganizationGoalDto;
import com.eco.environet.finance.dto.OrganizationGoalsSetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface OrganizationGoalService {
    OrganizationGoalDto create(OrganizationGoalDto newGoal);
    Page<OrganizationGoalsSetDto> findAll(Pageable pageable);
    OrganizationGoalsSetDto findCurrent();
    OrganizationGoalDto findById(Long id);
    OrganizationGoalDto update(OrganizationGoalDto oldGoal);
    void delete(Long id);
    OrganizationGoalsSetDto publish(OrganizationGoalsSetDto newValid);
}
