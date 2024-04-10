package com.eco.environet.users.services.impl;

import com.eco.environet.users.repository.UserRepository;
import com.eco.environet.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ModelMapper mapper = new ModelMapper();

//    public Optional<UserDto> activateUserAccount(Long id) {
//        try {
//            var user = repository.findById(id).orElseThrow();
//            if (user.getRole() != Role.USER) throw new Exception();
//            user.setEnabled(true);
//            repository.save(user);
//            return Optional.ofNullable(mapper.map(user, UserDto.class));
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//    }
}
