package com.eco.environet.users.services.impl;

import com.eco.environet.users.dto.AuthenticationRequest;
import com.eco.environet.users.dto.AuthenticationResponse;
import com.eco.environet.users.dto.RegisterRequest;
import com.eco.environet.users.exception.EmailExistsException;
import com.eco.environet.users.exception.IncorrectPasswordException;
import com.eco.environet.users.model.Role;
import com.eco.environet.users.model.User;
import com.eco.environet.users.repository.UserRepository;
import com.eco.environet.users.security.auth.JwtService;
import com.eco.environet.users.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var userExistsByEmail = repository.findByEmail(request.getEmail()).isPresent();
        var userExistsByUsername = repository.findByUsername(request.getUsername()).isPresent();

        if (userExistsByEmail || userExistsByUsername) {
            throw new EmailExistsException("User with the provided email or username already exists");
        }

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.values()[request.getRole()])
                .enabled(true)
                .lastPasswordResetDate(null)
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse changePassword(AuthenticationRequest request) {
        var user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            var user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            throw new IncorrectPasswordException("Authentication failed - incorrect password");
        }
    }
}
