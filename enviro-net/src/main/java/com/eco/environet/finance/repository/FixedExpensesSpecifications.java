package com.eco.environet.finance.repository;

import com.eco.environet.finance.model.FixedExpenses;
import com.eco.environet.finance.model.FixedExpensesType;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class FixedExpensesSpecifications {
    private FixedExpensesSpecifications () {}
    //  TODO - date range filter
    //  TODO - Creator search filter
    //  TODO - Employee search filter
    public static Specification<FixedExpenses> typeIn(List<FixedExpensesType> typeList){
        return ((root, query, criteriaBuilder) -> root.get("type").in(typeList));
    }
}
