package com.example.springbootjpa.controller.authentication;

import com.example.springbootjpa.dto.auth.AuthenticationRequest;
import com.example.springbootjpa.dto.auth.AuthenticationResponse;
import com.example.springbootjpa.dto.auth.RegisterRequest;
import com.example.springbootjpa.entity.ResponseObject;
import com.example.springbootjpa.service.impl.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ) {
        return authenticationService.register(request);
    }
}
