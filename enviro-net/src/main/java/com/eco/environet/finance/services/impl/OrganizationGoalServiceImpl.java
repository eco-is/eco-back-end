package com.eco.environet.finance.services.impl;

import com.eco.environet.finance.services.OrganizationGoalService;
import com.eco.environet.users.model.User;
import com.eco.environet.finance.dto.OrganizationGoalDto;
import com.eco.environet.finance.model.OrganizationGoal;
import com.eco.environet.finance.repository.OrganizationGoalRepository;
import com.eco.environet.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationGoalServiceImpl implements OrganizationGoalService {
    @Value("${baseFrontUrl}")
    private String baseFrontUrl;
    private final OrganizationGoalRepository repository;

    @Override
    public OrganizationGoalDto create(OrganizationGoalDto newGoal) {
        User creator = new User();
        creator.setId(newGoal.getCreator().getId());

        OrganizationGoal goal = OrganizationGoal.builder()
                .title(newGoal.getTitle())
                .description(newGoal.getDescription())
                .rationale(newGoal.getRationale())
                .priority(newGoal.getPriority())
                .validPeriod(newGoal.getValidPeriod())
                .creator(creator)
                .build();
        repository.save(goal);
        return Mapper.map(goal, OrganizationGoalDto.class);
    }

    @Override
    public Page<OrganizationGoalDto> findAll(Pageable pageable) {
        Page<OrganizationGoal> all = repository.findAll(pageable);
        return Mapper.mapPage(all, OrganizationGoalDto.class);
    }

    // TODO - findCurrent goals
    // TODO - endDate == null   // TODO -  first 5 startDate
    @Override
    public Page<OrganizationGoalDto> findCurrent(Pageable pageable) {
        Page<OrganizationGoal> all = repository.findAll(pageable);
        return Mapper.mapPage(all, OrganizationGoalDto.class);
    }

    @Override
    public OrganizationGoalDto findById(Long id) {
        OrganizationGoal goal = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization goal not found with ID: " + id));
        return Mapper.map(goal, OrganizationGoalDto.class);
    }

    @Override
    public OrganizationGoalDto update(OrganizationGoalDto oldGoal){
        OrganizationGoal goal = repository.findById(oldGoal.getId())
                .orElseThrow(() -> new EntityNotFoundException("Organization goal not found with ID: " + oldGoal.getId()));

        goal.setDescription(oldGoal.getDescription());
        goal.setRationale(oldGoal.getRationale());
        goal.setPriority(oldGoal.getPriority());
        if (!goal.getValidPeriod().isValid()){
            throw new IllegalArgumentException("Invalid organization goal date range");
        }
        goal.setValidPeriod(oldGoal.getValidPeriod());
        repository.save(goal);
        return Mapper.map(goal, OrganizationGoalDto.class);
    }

    @Override
    public void delete(Long id) {
        OrganizationGoal goal = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization goal not found with ID: " + id));

        repository.delete(goal);
    }
}
