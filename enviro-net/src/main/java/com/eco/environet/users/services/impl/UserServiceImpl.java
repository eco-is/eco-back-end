package com.eco.environet.users.services.impl;

import com.eco.environet.users.dto.UserDto;
import com.eco.environet.users.dto.UserInfoDto;
import com.eco.environet.users.model.Role;
import com.eco.environet.users.model.User;
import com.eco.environet.users.repository.UserRepository;
import com.eco.environet.users.repository.UserSpecifications;
import com.eco.environet.users.services.UserService;
import com.eco.environet.util.EnumMapper;
import com.eco.environet.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    public UserInfoDto findUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        UserInfoDto userDto = mapper.map(user, UserInfoDto.class, "password");
        return userDto;
    }

    @Override
    public UserInfoDto updateUser(UserInfoDto userInfoDto) {
        User user = userRepository.findById(userInfoDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userInfoDto.getId()));

        // Update the user information with data from userInfoDto
        user.setName(userInfoDto.getName());
        user.setSurname(userInfoDto.getSurname());
        user.setDateOfBirth(userInfoDto.getDateOfBirth());
        user.setGender(userInfoDto.getGender());
        user.setPhoneNumber(userInfoDto.getPhoneNumber());

        // TODO - email, password and username update implementation

        userRepository.save(user);
        UserInfoDto updatedUserInfoDto = mapper.map(user, UserInfoDto.class, "password");
        return updatedUserInfoDto;
    }

    @Override
    public Page<UserDto> findAllOrganizationMembers(String name, String surname, String email, Pageable pageable) {
        List<Role> organizationRoles = Role.getAllOrganizationRoles();

        Specification<User> spec = Specification.where(StringUtils.isBlank(name) ? null : UserSpecifications.nameLike(name))
                .and(StringUtils.isBlank(surname) ? null : UserSpecifications.surnameLike(surname))
                .and(StringUtils.isBlank(email) ? null : UserSpecifications.emailLike(email))
                .and(UserSpecifications.rolesIn(organizationRoles));

        Page<User> members = userRepository.findAll(spec,  pageable);
        Page<UserDto> memberDtos = mapper.mapPage(members, UserDto.class, "password");
        for (UserDto dto : memberDtos) {
            dto.setRole(EnumMapper.convertToTitleCase(dto.getRole()));
        }

        return memberDtos;
    }

    @Override
    public void removeOrganizationMember(Long memberId) {
        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + memberId));
        user.deactivate();
        userRepository.save(user);
    }
}
