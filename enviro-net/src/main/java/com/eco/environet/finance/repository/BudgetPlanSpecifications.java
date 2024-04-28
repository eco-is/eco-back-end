package com.eco.environet.finance.repository;

import com.eco.environet.finance.model.BudgetPlan;
import com.eco.environet.finance.model.BudgetPlanStatus;
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
    public static Specification<BudgetPlan> statusIn(List<BudgetPlanStatus> statusList, Long currentUserId) {
        return (root, query, criteriaBuilder) -> {
            if (statusList.contains(BudgetPlanStatus.DRAFT)) {
                return criteriaBuilder.or(
                        criteriaBuilder.and(
                                root.get("status").in(statusList),
                                criteriaBuilder.notEqual(root.get("status"), BudgetPlanStatus.DRAFT),
                                criteriaBuilder.notEqual(root.get("status"), BudgetPlanStatus.CLOSED)
                        ), criteriaBuilder.and(
                                criteriaBuilder.equal(root.get("status"), BudgetPlanStatus.DRAFT),
                                criteriaBuilder.equal(root.get("author").get("id"), currentUserId),
                                criteriaBuilder.notEqual(root.get("status"), BudgetPlanStatus.CLOSED)
                        )
                );
            } else {
                return root.get("status").in(statusList);
            }
        };
    }
}
