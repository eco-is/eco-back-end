package com.eco.environet.finance.services.impl;

import com.eco.environet.finance.dto.EmployeeDto;
import com.eco.environet.finance.dto.FixedExpensesDto;
import com.eco.environet.finance.model.DateRange;
import com.eco.environet.finance.model.FixedExpenses;
import com.eco.environet.finance.model.FixedExpensesType;
import com.eco.environet.finance.model.Salary;
import com.eco.environet.finance.repository.FixedExpensesRepository;
import com.eco.environet.finance.repository.SalaryRepository;
import com.eco.environet.finance.services.FixedExpensesService;
import com.eco.environet.users.model.Accountant;
import com.eco.environet.users.model.OrganizationMember;
import com.eco.environet.users.model.User;
import com.eco.environet.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FixedExpensesServiceImpl  implements FixedExpensesService {
    @Value("${baseFrontUrl}")
    private String baseFrontUrl;
    private final FixedExpensesRepository repository;
    private final SalaryRepository salaryRepository;


    @Override
    public FixedExpensesDto create(FixedExpensesDto newFixedExpenseDto){
        Accountant creator = new Accountant();
        creator.setId(newFixedExpenseDto.getCreator().getId());

        // TODO - check if it already exists for last month
        // Determine the period for the last month
        LocalDate endDate = LocalDate.now().withDayOfMonth(1);
        LocalDate startDate = endDate.minusMonths(1);
        DateRange period = new DateRange(startDate, endDate);

        FixedExpensesType fixedExpensesType;
        try {
            fixedExpensesType = FixedExpensesType.valueOf(newFixedExpenseDto.getType());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid type provided: " + newFixedExpenseDto.getType());
        }

        FixedExpenses newExpense = FixedExpenses.fixedExpensesBuilder()
                .type(fixedExpensesType)
                .period(period)
                .amount(newFixedExpenseDto.getAmount())
                .creator(creator)
                .createdOn(new Timestamp(System.currentTimeMillis()))
                .description(newFixedExpenseDto.getDescription())
                .build();

        if (fixedExpensesType == FixedExpensesType.SALARY && newFixedExpenseDto.getEmployee() == null){
            throw new IllegalArgumentException("Invalid employee for salary provided! Employee can't be null for salary!");
        } else {
            if (fixedExpensesType == FixedExpensesType.SALARY) {
                User employeeUser = new User();
                employeeUser.setId(newFixedExpenseDto.getEmployee().getId());
                employeeUser.setEmail(newFixedExpenseDto.getEmployee().getEmail());

                // Salary
                OrganizationMember employee = new OrganizationMember(
                        employeeUser,
                        newFixedExpenseDto.getEmployee().getWage(),
                        newFixedExpenseDto.getEmployee().getWorkingHours(),
                        newFixedExpenseDto.getEmployee().getOvertimeWage());
                Salary newSalary = new Salary(newExpense, employee, newFixedExpenseDto.getOvertimeHours());
                salaryRepository.save(newSalary);
                newExpense.setId(newSalary.getId());
                // TODO employee is null when returned
                return Mapper.map(newExpense, FixedExpensesDto.class);
            } else {
                repository.save(newExpense);
                return Mapper.map(newExpense, FixedExpensesDto.class);
            }
        }
    }

    // TODO
//    @Override //filters: type, periods, employees,
//    public Page<FixedExpensesDto> findAll(List<String> types, Pageable pageable){
//
//    }

    @Override
    public FixedExpensesDto findById(Long id){
        FixedExpenses expense = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fixed expense not found with ID: " + id));
        return Mapper.map(expense, FixedExpensesDto.class);
    }

    @Override
    public FixedExpensesDto update(FixedExpensesDto fixedExpenseDto){
        FixedExpenses updatedExpense = repository.findById(fixedExpenseDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Fixed expense not found with ID: " + fixedExpenseDto.getId()));
        FixedExpensesType fixedExpensesType;
        try {
            fixedExpensesType = FixedExpensesType.valueOf(fixedExpenseDto.getType());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid type provided: " + fixedExpenseDto.getType());
        }

        if (!fixedExpenseDto.getType().equals("SALARY")){
            updatedExpense.setAmount(fixedExpenseDto.getAmount());
        }
        updatedExpense.setType(fixedExpensesType);
        updatedExpense.setDescription(fixedExpenseDto.getDescription());
        repository.save(updatedExpense);
        return Mapper.map(updatedExpense, FixedExpensesDto.class);
    }

    @Override
    public void delete(Long id){
        FixedExpenses expense = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fixed expense not found with ID: " + id));
        repository.delete(expense);
    }
}
