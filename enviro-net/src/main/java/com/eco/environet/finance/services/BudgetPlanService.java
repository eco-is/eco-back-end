package com.eco.environet.finance.services;

import com.eco.environet.finance.dto.BudgetPlanDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BudgetPlanService {
    BudgetPlanDto create(BudgetPlanDto newBudgetPlan);
    Page<BudgetPlanDto> findAll(String name, List<String> statuses, Pageable pageable);
    BudgetPlanDto findById(Long id);
    BudgetPlanDto update(BudgetPlanDto budgetPlan);
    void archive(BudgetPlanDto budgetPlan);
    void close(BudgetPlanDto budgetPlan);
}
