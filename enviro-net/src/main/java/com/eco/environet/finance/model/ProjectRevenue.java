package com.eco.environet.finance.model;

import com.eco.environet.projects.model.Project;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "projectRevenueBuilder")
@Table(name="project_revenues", schema = "finance")
public class ProjectRevenue extends Revenue {
    @ManyToOne
    private Project project;
}