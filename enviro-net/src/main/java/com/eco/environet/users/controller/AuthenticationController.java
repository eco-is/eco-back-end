package com.eco.environet.users.controller;

import com.eco.environet.users.dto.AuthenticationRequest;
import com.eco.environet.users.dto.AuthenticationResponse;
import com.eco.environet.users.dto.RegisterRequest;
import com.eco.environet.users.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authorization", description = "Log In and Register")
public class AuthenticationController {

    private final AuthenticationService service;

    @Operation(summary = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registered",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Email or username already exist",
                    content = @Content) })
    @PostMapping(value="/register", consumes="application/json")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        var result = service.register(request);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Authenticate user by username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logged in",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Username or password incorrect",
                    content = @Content) })
    @PostMapping(value = "/authenticate", consumes = "application/json")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        var result = service.authenticate(request);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // primer zasticene metode
    // ukoliko zelite da napravite metodu kojoj moze da pristupi vise razlicitih uloga mozete napisati sledece:
    //@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'REGISTERED_USER')")
    @GetMapping(value = "/helloworld")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String helloWorld() {
        return "Hello world";
    }
}
