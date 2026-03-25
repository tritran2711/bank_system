package com.example.bank_system.service;

import com.example.bank_system.dto.LoginRequest;
import com.example.bank_system.entity.Role;
import com.example.bank_system.entity.User;
import com.example.bank_system.repository.UserRepository;
import com.example.bank_system.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    //  REGISTER
    public String register(LoginRequest request) {

        // check username tồn tại
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // tạo user mới
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())) // encode
                .role(Role.CUSTOMER)
                .build();

        userRepository.save(user);

        return "REGISTER SUCCESS";
    }

    //  LOGIN
    public String login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
    //  tạo token
        return jwtService.generateToken(user.getUsername());
    }
}
