package com.eco.environet.finance.model;

import com.eco.environet.projects.model.Project;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "projectDonationBuilder")
@Table(name="project_donations", schema = "finance")
public class ProjectDonation extends Donation {
    @ManyToOne
    private Project project;
}
