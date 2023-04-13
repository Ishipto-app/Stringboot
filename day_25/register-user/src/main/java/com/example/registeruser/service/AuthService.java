package com.example.registeruser.service;

import com.example.registeruser.entity.TokenConfirm;
import com.example.registeruser.entity.User;
import com.example.registeruser.repository.TokenConfirmRepository;
import com.example.registeruser.repository.UserRepository;
import com.example.registeruser.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenConfirmRepository tokenConfirmRepository;

    public String register(RegisterRequest request) {
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
        System.out.println(request.getName());
        return null;
    }
    public String saveTokenConfirm(User user) {
        return null;

    }
    public String confirm(String token) {
        return null;
    }
}
