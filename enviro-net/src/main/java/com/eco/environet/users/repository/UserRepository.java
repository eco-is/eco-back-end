package com.eco.environet.users.repository;

import com.eco.environet.users.model.Role;
import com.eco.environet.users.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.role  IN :organizationRoles")
    Page<User> findAllByRoles(List<Role> organizationRoles, Pageable pageable);
}
