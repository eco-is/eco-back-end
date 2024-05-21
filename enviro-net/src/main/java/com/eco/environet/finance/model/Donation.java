package com.eco.environet.finance.model;

import com.eco.environet.users.model.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "donationBuilder")
@Table(name="donations", schema = "finance")
public class Donation extends Revenue{
    @ManyToOne
    private User donator;
}
