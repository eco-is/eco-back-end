package com.eco.environet.finance.model;

import com.eco.environet.users.model.Accountant;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@Builder
@Table(name="budget_plan", schema = "finance")
public class BudgetPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BudgetPlanStatus status;

    @Column(name = "last_updated_on_date", nullable = false)
    private Timestamp lastUpdatedOnDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="startDate", column=@Column(name="start_date", nullable = false)),
            @AttributeOverride(name="endDate", column=@Column(name="end_date", nullable = false))
    })
    private DateRange fiscalDateRange;

    @ManyToOne
    private Accountant author;

    public BudgetPlan(Long id, String name, String description, BudgetPlanStatus status, Timestamp lastUpdatedOnDate, DateRange fiscalDateRange, Accountant author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.lastUpdatedOnDate = lastUpdatedOnDate;
        this.fiscalDateRange = fiscalDateRange;
        this.author = author;
    }

    public void approve(){
        this.status = BudgetPlanStatus.APPROVED;
        this.lastUpdatedOnDate = new Timestamp(System.currentTimeMillis());
    }
    public void reject(){
        this.status = BudgetPlanStatus.REJECTED;
        this.lastUpdatedOnDate = new Timestamp(System.currentTimeMillis());
    }
    public void archive(){
        this.status = BudgetPlanStatus.ARCHIVED;
        this.lastUpdatedOnDate = new Timestamp(System.currentTimeMillis());
    }
    public void close(){
        this.status = BudgetPlanStatus.CLOSED;
        this.lastUpdatedOnDate = new Timestamp(System.currentTimeMillis());
    }
}
