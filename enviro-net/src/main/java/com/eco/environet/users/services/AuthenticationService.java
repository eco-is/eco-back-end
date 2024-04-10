package com.eco.environet.users.services;

import com.eco.environet.users.dto.AuthenticationRequest;
import com.eco.environet.users.dto.AuthenticationResponse;
import com.eco.environet.users.dto.RegisterRequest;
import com.eco.environet.users.model.Role;
import com.eco.environet.users.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse changePassword(AuthenticationRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
