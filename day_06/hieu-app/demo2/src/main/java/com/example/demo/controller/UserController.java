package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserDto;
import com.example.demo.request.CheckUserRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDto loginUser(LoginRequest request){
        return userService.login(request);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> postBmiByData(@Valid @RequestBody CheckUserRequest request) {
//
//        User user = userService.checkUserServer(request);
//        return ResponseEntity.ok(user);
//    }
}
