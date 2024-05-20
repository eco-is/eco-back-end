package com.eco.environet.finance.repository;

import com.eco.environet.finance.model.FixedExpenses;
import com.eco.environet.finance.model.FixedExpensesType;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class FixedExpensesSpecifications {
    private FixedExpensesSpecifications () {}
    //  TODO - period filter
//    public static Specification<FixedExpenses> periodBetween(Instant startDate, Instant endDate) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("period").get("startDate"), startDate, endDate);
//    }
    public static Specification<FixedExpenses> creatorIn(List<Long> creatorList){
        return ((root, query, criteriaBuilder) -> root.get("creator").get("id").in(creatorList));
    }
    public static Specification<FixedExpenses> employeeIn(List<Long> employeeList){
        return ((root, query, criteriaBuilder) -> root.get("employee").get("id").in(employeeList));
    }
    public static Specification<FixedExpenses> typeIn(List<FixedExpensesType> typeList){
        return ((root, query, criteriaBuilder) -> root.get("type").in(typeList));
    }
}
