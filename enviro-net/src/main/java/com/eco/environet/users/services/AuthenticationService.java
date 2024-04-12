package com.eco.environet.users.services;

import com.eco.environet.users.dto.AuthenticationRequest;
import com.eco.environet.users.dto.AuthenticationResponse;
import com.eco.environet.users.dto.VerifyMemberRequest;
import com.eco.environet.users.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthenticationResponse registerUser(RegisterRequest request);
    void registerOrganizationMember(RegisterRequest request);
    AuthenticationResponse changePassword(AuthenticationRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void verifyOrganizationMember(VerifyMemberRequest request);
}
