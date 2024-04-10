package com.eco.environet.users.services.impl;

import com.eco.environet.users.dto.AuthenticationRequest;
import com.eco.environet.users.dto.AuthenticationResponse;
import com.eco.environet.users.dto.RegisterRequest;
import com.eco.environet.users.exception.CredentialsTakenException;
import com.eco.environet.users.model.Role;
import com.eco.environet.users.model.User;
import com.eco.environet.users.repository.UserRepository;
import com.eco.environet.users.security.auth.JwtService;
import com.eco.environet.users.services.AuthenticationService;
import com.eco.environet.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${baseFrontUrl}")
    private String baseFrontUrl;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailSender emailSender;

    public AuthenticationResponse registerUser(RegisterRequest request) {
        var userExistsByEmail = repository.findByEmail(request.getEmail()).isPresent();
        var userExistsByUsername = repository.findByUsername(request.getUsername()).isPresent();
        Role role = Role.values()[request.getRole()];

        if (role != Role.REGISTERED_USER) {
            throw new IllegalArgumentException("Role is not registered user");
        }
        if (userExistsByEmail) {
            throw new CredentialsTakenException("Email already used.");
        }
        if (userExistsByUsername) {
            throw new CredentialsTakenException("Username already taken.");
        }

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(role)
                .enabled(true)
                .lastPasswordResetDate(null)
                .active(true)
                .build();


        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void registerOrganizationMember(RegisterRequest request) {
        validateMember(request);

        var user = User.builder()
                .username(request.getEmail())
                .password("")
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.values()[request.getRole()])
                .enabled(false)
                .lastPasswordResetDate(null)
                .active(true)
                .build();

        repository.save(user);

        sendConfirmationEmail(user);
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
    }

    // TODO validate member
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

    private void validateMember(RegisterRequest request) {
        Role role = Role.values()[request.getRole()];
        if (!role.isOrganizationMember()) {
            throw new IllegalArgumentException("Role is not an organization member");
        }

        var userExistsByEmail = repository.findByEmail(request.getEmail()).isPresent();
        if (userExistsByEmail) {
            throw new CredentialsTakenException("Email already used.");
        }
    }

    private void sendConfirmationEmail(User user) {
        var jwtToken = jwtService.generateToken(user);
        String confirmationLink = baseFrontUrl + "/confirm-email?token=" + jwtToken;

        String emailBody = "Hello " + user.getName() + ",\n\n";
        emailBody += "Thank you for joining our organization. Please click the link below to complete your registration:\n\n";
        emailBody += confirmationLink + "\n\n";
        emailBody += "This link is valid for 24 hours. If the link expires, please contact us for assistance.\n";
        emailBody += "If you did not request this registration, please ignore this email.\n\n";
        emailBody += "Best regards,\nYour EnviroNet Team";

        emailSender.sendEmail(user.getEmail(), "Finalize Registration", emailBody);
    }
}
