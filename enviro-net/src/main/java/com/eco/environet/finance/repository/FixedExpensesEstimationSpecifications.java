package com.eco.environet.finance.repository;

import com.eco.environet.finance.model.FixedExpensesEstimation;
import com.eco.environet.finance.model.FixedExpensesType;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class FixedExpensesEstimationSpecifications {
    private FixedExpensesEstimationSpecifications (){}
//    public static Specification<FixedExpenses> budgetPlanIn(Long budgetPlanId){
//        return ((root, query, criteriaBuilder) -> root.get("budgetPlan").get("id").in(budgetPlanId));
//    }
    public static Specification<FixedExpensesEstimation> employeeIn(List<Long> employeeList){
        return ((root, query, criteriaBuilder) -> root.get("employee").get("id").in(employeeList));
    }
    public static Specification<FixedExpensesEstimation> typeIn(List<FixedExpensesType> typeList){
        return ((root, query, criteriaBuilder) -> root.get("type").in(typeList));
    }
}
