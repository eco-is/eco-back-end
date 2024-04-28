package com.eco.environet.finance.repository;

import com.eco.environet.finance.model.BudgetPlan;
import com.eco.environet.finance.model.BudgetPlanStatus;
import com.eco.environet.users.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
public class BudgetPlanSpecifications {
    private BudgetPlanSpecifications() {}

    public static Specification<BudgetPlan> nameLike(String name) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    //  TODO - date range filter
    //  TODO - Author search filter
    public static Specification<BudgetPlan> statusIn(List<BudgetPlanStatus> statusList){
        return (root, query, criteriaBuilder) -> root.get("status").in(statusList);
    }
}
