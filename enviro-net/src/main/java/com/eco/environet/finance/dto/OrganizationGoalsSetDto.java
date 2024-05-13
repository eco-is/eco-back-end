package com.eco.environet.finance.dto;

import com.eco.environet.finance.model.DateRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationGoalsSetDto {
    private DateRange validPeriod;
    private List<OrganizationGoalDto> goals;
}
