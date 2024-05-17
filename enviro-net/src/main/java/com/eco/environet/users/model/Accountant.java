package com.eco.environet.users.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@Builder(builderMethodName = "accountantBuilder")
@Table(name="accountants", schema = "users")
public class Accountant extends User {

    @Column(name = "wage", nullable = false)
    private double wage;

    public Accountant(Long id, String name, String surname, String email, String username, String password, String phoneNumber,
                      Timestamp dateOfBirth, Gender gender, Timestamp lastPasswordResetDate, Role role, boolean enabled, boolean active, double wage) {
        super(id, name, surname, email, username, password, phoneNumber, dateOfBirth, gender, lastPasswordResetDate, role, enabled, active);
        this.wage = wage;
    }
    public Accountant(User user, double wage){
        super(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getUsername(), user.getPassword(), user.getPhoneNumber(), user.getDateOfBirth(), user.getGender(), user.getLastPasswordResetDate(), user.getRole(), user.isEnabled(), user.isActive());
        this.wage = wage;
    }
    public Accountant(double wage){
        this.wage = wage;
    }
}
