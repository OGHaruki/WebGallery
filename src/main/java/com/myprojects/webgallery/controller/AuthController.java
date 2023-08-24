package com.myprojects.webgallery.controller;

import com.myprojects.webgallery.config.AuthenticationResponse;
import com.myprojects.webgallery.payload.LoginDto;
import com.myprojects.webgallery.payload.RegisterDto;
import com.myprojects.webgallery.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterDto registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginDto loginRequest
    ) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}
