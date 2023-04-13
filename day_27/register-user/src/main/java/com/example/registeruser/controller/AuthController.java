package com.example.registeruser.controller;

import com.example.registeruser.entity.User;
import com.example.registeruser.request.RegisterRequest;
import com.example.registeruser.service.AuthService;
import com.example.registeruser.service.CustomUserDetailsSerivce;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam String token) {
        return authService.confirm(token);
    }
}
