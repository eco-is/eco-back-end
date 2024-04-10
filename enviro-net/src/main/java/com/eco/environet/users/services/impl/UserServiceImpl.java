package com.eco.environet.users.services.impl;

import com.eco.environet.users.dto.UserDto;
import com.eco.environet.users.model.Role;
import com.eco.environet.users.model.User;
import com.eco.environet.users.repository.UserRepository;
import com.eco.environet.users.services.UserService;
import com.eco.environet.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    public Page<UserDto> findAllOrganizationMembers(Pageable pageable) {
        List<Role> organizationRoles = Role.getAllOrganizationRoles();
        Page<User> members = userRepository.findAllByRoles(organizationRoles, pageable);
        return mapper.mapPage(members, UserDto.class, "password");
    }

    @Override
    public void removeOrganizationMember(Long memberId) {
        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + memberId));
        user.deactivate();
        userRepository.save(user);
    }
}
