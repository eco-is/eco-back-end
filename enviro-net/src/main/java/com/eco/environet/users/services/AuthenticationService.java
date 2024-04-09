package com.eco.environet.users.services;

import com.eco.environet.users.dto.AuthenticationRequest;
import com.eco.environet.users.dto.AuthenticationResponse;
import com.eco.environet.users.dto.RegisterRequest;
import com.eco.environet.users.model.Role;
import com.eco.environet.users.model.User;
import com.eco.environet.users.repository.UserRepository;
import com.eco.environet.users.security.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Optional<AuthenticationResponse> register(RegisterRequest request) {

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
        var usernameOrEmailExists = (
                repository.findByEmail(request.getEmail()).isPresent() ||
                        repository.findByUsername(request.getUsername()).isPresent());
        if (usernameOrEmailExists) {
            return Optional.empty();
        }
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return Optional.ofNullable(AuthenticationResponse.builder()
                .token(jwtToken)
                .build());
    }

    public Optional<AuthenticationResponse> changePassword(AuthenticationRequest request) {
        var user = (repository.findByUsername(request.getUsername()));
        if (user.isEmpty()) {
            return Optional.empty();
        }

        user.get().setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(user.get());

        var jwtToken = jwtService.generateToken(user.get());
        return Optional.ofNullable(AuthenticationResponse.builder()
                .token(jwtToken)
                .build());
    }

    public Optional<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            var user = repository.findByUsername(request.getUsername()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return Optional.ofNullable(AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build());
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }
}
