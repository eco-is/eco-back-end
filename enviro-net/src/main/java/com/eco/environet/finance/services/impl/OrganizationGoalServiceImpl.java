package com.eco.environet.finance.services.impl;

import com.eco.environet.finance.services.OrganizationGoalService;
import com.eco.environet.users.model.User;
import com.eco.environet.finance.dto.OrganizationGoalDto;
import com.eco.environet.finance.dto.OrganizationGoalsSetDto;
import com.eco.environet.finance.model.DateRange;
import com.eco.environet.finance.model.OrganizationGoal;
import com.eco.environet.finance.repository.OrganizationGoalRepository;
import com.eco.environet.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Page<OrganizationGoalsSetDto> findAll(Pageable pageable) {
        // Increase pageSize by a factor of 5, because one set can have 3-5 goals
        Pageable adjustedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize() * 5,
                pageable.getSort()
        );
        Page<OrganizationGoal> allGoals = repository.findAll(adjustedPageable);

        // Group organization goals by their validity periods
        Map<DateRange, List<OrganizationGoal>> goalsByValidityPeriod = allGoals.getContent().stream()
                .collect(Collectors.groupingBy(OrganizationGoal::getValidPeriod));

        // Convert the grouped goals into OrganizationGoalsSetDto
        List<OrganizationGoalsSetDto> goalsSets = goalsByValidityPeriod.entrySet().stream()
                .map(entry -> OrganizationGoalsSetDto.builder()
                        .validPeriod(entry.getKey())
                        .goals(entry.getValue().stream()
                                .map(goal -> Mapper.map(goal, OrganizationGoalDto.class))
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return new PageImpl<>(goalsSets, pageable, allGoals.getTotalElements());
    }

    @Override
    public Page<OrganizationGoalDto> findCurrent(Pageable pageable) {
        Page<OrganizationGoal> currentGoals = repository.findByValidPeriodEndDateIsNull(pageable);
        return Mapper.mapPage(currentGoals, OrganizationGoalDto.class);
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
