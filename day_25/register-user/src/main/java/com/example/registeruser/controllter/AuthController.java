package com.example.registeruser.controllter;


import com.example.registeruser.request.RegisterRequest;
import com.example.registeruser.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam String token) {
        return authService.confirm(token);
    }
}
