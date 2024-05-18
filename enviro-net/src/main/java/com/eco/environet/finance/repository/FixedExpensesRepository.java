package com.eco.environet.finance.repository;

import com.eco.environet.finance.model.FixedExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedExpensesRepository extends JpaRepository<FixedExpenses, Long> {
}
