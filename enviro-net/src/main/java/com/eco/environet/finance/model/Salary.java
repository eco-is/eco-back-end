package com.eco.environet.finance.model;

import com.eco.environet.users.model.OrganizationMember;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue("SALARY")
@Builder(builderMethodName = "salaryBuilder")
public class Salary extends FixedExpenses {
    @ManyToOne
    private OrganizationMember employee;

    @Column(name = "overtime_hours")
    private double overtimeHours = 0;

    public Salary(FixedExpenses fixedExpenses, OrganizationMember employee, double overtimeHours) {
        super(fixedExpenses.getId(), fixedExpenses.getType(), fixedExpenses.getPeriod(), fixedExpenses.getAmount(), fixedExpenses.getCreator(), fixedExpenses.getCreatedOn(), fixedExpenses.getDescription());
        this.employee = employee;
        this.overtimeHours = overtimeHours;
        this.setAmount(calculateAmount()); // Calculate amount
    }
    public Salary(OrganizationMember employee, double overtimeHours) {
        super();
        this.employee = employee;
        this.overtimeHours = overtimeHours;
        this.setAmount(calculateAmount()); // Calculate amount
    }

    // Method to calculate amount
    private double calculateAmount() {
        double amount = 0;
        if (employee != null) {
            double regularPayment = employee.getWage() * employee.getWorkingHours() * this.getPeriod().getWorkingDays();
            double overtimePayment = employee.getOvertimeWage() * overtimeHours;
            amount = regularPayment + overtimePayment;
        }
        return amount;
    }
}
