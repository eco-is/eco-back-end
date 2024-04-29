package com.eco.environet.finance.services;

import com.eco.environet.finance.dto.OrganizationGoalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface OrganizationGoalService {
    OrganizationGoalDto create(OrganizationGoalDto newGoal);
    Page<OrganizationGoalDto> findAll(Pageable pageable);
    Page<OrganizationGoalDto> findCurrent(Pageable pageable);
    OrganizationGoalDto findById(Long id);
    OrganizationGoalDto update(OrganizationGoalDto oldGoal);
    void delete(Long id);
}
