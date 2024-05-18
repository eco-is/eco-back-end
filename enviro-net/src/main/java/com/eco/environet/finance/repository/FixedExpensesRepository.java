package com.eco.environet.finance.repository;

import com.eco.environet.finance.model.FixedExpenses;
import com.eco.environet.finance.model.FixedExpensesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FixedExpensesRepository extends JpaRepository<FixedExpenses, Long> {
    @Query("SELECT f FROM FixedExpenses f WHERE f.type = :type AND f.period.startDate = :startDate AND f.period.endDate = :endDate")
    List<FixedExpenses> findByTypeAndPeriod(@Param("type") FixedExpensesType type, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
