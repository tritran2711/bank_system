package com.example.bank_system.controller;

import com.example.bank_system.dto.LoginRequest;
import com.example.bank_system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //register
    @PostMapping("/register")
    public String register(@RequestBody LoginRequest request) {
        return authService.register(request);
    }

    //login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}